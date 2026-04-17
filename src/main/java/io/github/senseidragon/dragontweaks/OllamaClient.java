package io.github.senseidragon.dragontweaks;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OllamaClient {

    private static final ExecutorService EXECUTOR =
        Executors.newSingleThreadExecutor(r -> new Thread(r, "dragontweaks-llm"));

    private static final HttpClient HTTP = HttpClient.newBuilder()
        .executor(EXECUTOR)
        .build();

    private static final Gson GSON = new Gson();

    private static final String SYSTEM_PROMPT =
        "You are a helpful assistant NPC in a Minecraft colony. " +
        "You are aware of the time of day and weather. " +
        "Respond in character, briefly (2-3 sentences).";

    static String timeOfDay(long dayTime) {
        long t = dayTime % 24000;
        if (t < 6000) return "MORNING";
        if (t < 12000) return "AFTERNOON";
        if (t < 18000) return "EVENING";
        return "NIGHT";
    }

    static String weather(boolean raining, boolean thundering) {
        if (thundering) return "THUNDERSTORM";
        if (raining) return "RAINING";
        return "CLEAR";
    }

    static String parseResponse(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
        if (!obj.has("response") || obj.get("response").isJsonNull()) {
            throw new IllegalArgumentException("Ollama error response: " + json);
        }
        return obj.get("response").getAsString();
    }

    private static String buildRequestBody(String model, String prompt) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", model);
        obj.addProperty("system", SYSTEM_PROMPT);
        obj.addProperty("prompt", prompt);
        obj.addProperty("stream", false);
        return GSON.toJson(obj);
    }

    static void sendFallback(ServerPlayer player, Component entityName) {
        player.sendSystemMessage(
            Component.literal("[").append(entityName)
                .append("]: My thoughts escape me for the moment.")
        );
    }

    public static void shutdown() {
        EXECUTOR.shutdownNow();
    }

    public static void query(MinecraftServer server, ServerPlayer player,
                             Component entityName, String message,
                             String timeOfDay, String weather) {
        if (!Config.LLM_ENABLED.get()) {
            server.execute(() -> sendFallback(player, entityName));
            return;
        }

        String prompt = "Player said: " + message + "\nTime: " + timeOfDay + "\nWeather: " + weather;
        String requestBody = buildRequestBody(Config.LLM_MODEL.get(), prompt);

        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                .uri(URI.create(Config.LLM_ENDPOINT.get()))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        } catch (IllegalArgumentException e) {
            DragonTweaks.LOGGER.error("[OllamaClient] Invalid endpoint URI: {}", e.getMessage());
            server.execute(() -> sendFallback(player, entityName));
            return;
        }

        HTTP.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .orTimeout(Config.LLM_TIMEOUT_SECONDS.get(), TimeUnit.SECONDS)
            .thenApply(response -> parseResponse(response.body()))
            .thenAccept(reply -> server.execute(() ->
                player.sendSystemMessage(
                    Component.literal("[").append(entityName).append("]: " + reply)
                )
            ))
            .exceptionally(ex -> {
                DragonTweaks.LOGGER.warn("[OllamaClient] LLM request failed: {}", ex.getMessage());
                server.execute(() -> sendFallback(player, entityName));
                return null;
            });
    }
}
