package io.github.senseidragon.dragontweaks;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OllamaClient {

    private static ExecutorService EXECUTOR = newExecutor();
    private static HttpClient HTTP = newHttpClient(EXECUTOR);

    private static ExecutorService newExecutor() {
        return Executors.newSingleThreadExecutor(r -> new Thread(r, "dragontweaks-llm"));
    }

    private static HttpClient newHttpClient(ExecutorService exec) {
        return HttpClient.newBuilder().executor(exec).build();
    }

    private static void ensureAlive() {
        if (EXECUTOR.isShutdown()) {
            EXECUTOR = newExecutor();
            HTTP = newHttpClient(EXECUTOR);
        }
    }

    private static final Gson GSON = new Gson();

    private static String buildSystemPrompt(String npcName, String role, String playerName,
                                             String timeOfDay, String weather, String surroundings) {
        String personaBlock = RolePersona.getPersonaBlock(role);
        return "You are " + npcName + ", a " + role + " in a medieval village.\n" +
               (personaBlock.isEmpty() ? "" : personaBlock + "\n") +
               "You live in a world where creepers explode, endermen are unsettling tall dark figures that dislike eye contact, " +
               "zombies and skeletons roam at night, and emeralds are common currency. " +
               "Farming, mining, and crafting are everyday activities. " +
               "Refer to all of these as natural parts of your world — they are real to you. " +
               "Never reference \"the game\", \"players\", \"code\", or anything that breaks the sense that this is a real place you live in.\n" +
               "The person speaking to you is named " + playerName + ".\n" +
               "It is currently " + timeOfDay + " and the weather is " + weather + ". " +
               "You are aware of your surroundings but only mention them when it feels natural.\n" +
               "Nearby you can see: " + surroundings + ".\n" +
               "Respond as " + npcName + " in 1-2 short sentences. Never break character. Never say you are an AI.";
    }

    static String timeOfDay(long dayTime) {
        long t = dayTime % 24000;
        if (t < 6000) return "MORNING";
        if (t < 12000) return "AFTERNOON";
        if (t < 18000) return "EVENING";
        return "NIGHT";
    }

    static Map<String, Boolean> scanSurroundingsRaw(ServerLevel level, AssistantEntity npc) {
        double radius = Config.NPC_AWARENESS_RADIUS.get().doubleValue();
        AABB box = AABB.ofSize(npc.position(), radius * 2, radius * 2, radius * 2);
        String category = Config.NPC_AWARENESS_CATEGORY.get();

        Map<String, Boolean> result = new LinkedHashMap<>();
        for (Entity entity : level.getEntitiesOfClass(Entity.class, box,
                e -> !(e instanceof AssistantEntity) && !(e instanceof Player) && !(e instanceof ItemEntity))) {
            MobCategory mob = entity.getType().getCategory();
            boolean include = switch (category) {
                case "HOSTILE" -> mob == MobCategory.MONSTER;
                case "ALL"     -> mob != MobCategory.MISC;
                default        -> mob == MobCategory.CREATURE || mob == MobCategory.AMBIENT
                               || mob == MobCategory.WATER_CREATURE || mob == MobCategory.WATER_AMBIENT;
            };
            if (!include) continue;
            String name = entityDisplayName(entity);
            result.putIfAbsent(name, mob == MobCategory.MONSTER);
        }
        return result;
    }

    static String scanSurroundings(ServerLevel level, AssistantEntity npc) {
        Map<String, Boolean> raw = scanSurroundingsRaw(level, npc);
        if (raw.isEmpty()) return "nothing notable nearby";
        return String.join(", ", raw.keySet());
    }

    private static String entityDisplayName(Entity entity) {
        String id = entity.getType().getDescriptionId(); // e.g. "entity.minecraft.pig"
        String[] parts = id.split("\\.");
        return parts[parts.length - 1].replace("_", " ");
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

    private static String buildRequestBody(String model, String message, String npcName, String role,
                                            String playerName, String timeOfDay, String weather,
                                            String surroundings) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", model);
        obj.addProperty("system", buildSystemPrompt(npcName, role, playerName, timeOfDay, weather, surroundings));
        obj.addProperty("prompt", message);
        obj.addProperty("stream", false);
        obj.addProperty("think", false);
        JsonObject options = new JsonObject();
        options.addProperty("num_predict", 100);
        obj.add("options", options);
        return GSON.toJson(obj);
    }

    static void sendFallback(ServerPlayer player, Component entityName) {
        player.sendSystemMessage(
            Component.literal("[").append(entityName)
                .append("]: My thoughts escape me for the moment.")
        );
    }

    public static void warmup() {
        ensureAlive();
        JsonObject obj = new JsonObject();
        obj.addProperty("model", Config.LLM_MODEL.get());
        obj.addProperty("prompt", "ping");
        obj.addProperty("stream", false);
        obj.addProperty("think", false);
        JsonObject options = new JsonObject();
        options.addProperty("num_predict", 1);
        obj.add("options", options);
        String body = GSON.toJson(obj);

        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                .uri(URI.create(Config.LLM_ENDPOINT.get()))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        } catch (IllegalArgumentException e) {
            DragonTweaks.LOGGER.error("[OllamaClient] Warmup skipped — invalid endpoint URI: {}", e.getMessage());
            return;
        }

        HTTP.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenAccept(r -> DragonTweaks.LOGGER.info("[OllamaClient] LLM ready"))
            .exceptionally(e -> {
                DragonTweaks.LOGGER.warn("[OllamaClient] LLM unreachable — fallbacks will be used ({})", e.getMessage());
                return null;
            });
    }

    public static void shutdown() {
        EXECUTOR.shutdownNow();
    }

    public static void observe(MinecraftServer server, ServerPlayer player, Component entityName,
                               String role, String timeOfDay, String weather, String surroundings,
                               String whatChanged) {
        ensureAlive();
        if (!Config.LLM_ENABLED.get()) return;

        String npcName = entityName.getString();
        String playerName = player.getGameProfile().getName();
        String systemPrompt = buildSystemPrompt(npcName, role, playerName, timeOfDay, weather, surroundings);
        String prompt = "You just noticed: " + whatChanged +
                        ". React in character in 1-2 short sentences. Address " + playerName + " directly.";

        JsonObject obj = new JsonObject();
        obj.addProperty("model", Config.LLM_MODEL.get());
        obj.addProperty("system", systemPrompt);
        obj.addProperty("prompt", prompt);
        obj.addProperty("stream", false);
        obj.addProperty("think", false);
        JsonObject options = new JsonObject();
        options.addProperty("num_predict", 100);
        obj.add("options", options);
        String requestBody = GSON.toJson(obj);

        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                .uri(URI.create(Config.LLM_ENDPOINT.get()))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        } catch (IllegalArgumentException e) {
            DragonTweaks.LOGGER.error("[OllamaClient] observe() — invalid endpoint URI: {}", e.getMessage());
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
                DragonTweaks.LOGGER.warn("[OllamaClient] observe() failed: {}", ex.getMessage());
                return null;
            });
    }

    public static void query(MinecraftServer server, ServerPlayer player,
                             Component entityName, String message, String role,
                             String timeOfDay, String weather, String surroundings,
                             UUID npcId) {
        ensureAlive();
        if (!Config.LLM_ENABLED.get()) {
            server.execute(() -> sendFallback(player, entityName));
            return;
        }

        String npcName = entityName.getString();
        String playerName = player.getGameProfile().getName();
        String history = ConversationMemory.getHistory(npcId, playerName);
        String prompt = history.isEmpty()
            ? playerName + " says: " + message
            : "[Prior conversation:]\n" + history + "\n\n" + playerName + " says: " + message;
        String requestBody = buildRequestBody(Config.LLM_MODEL.get(), prompt, npcName, role, playerName, timeOfDay, weather, surroundings);

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

        try {
            HTTP.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .orTimeout(Config.LLM_TIMEOUT_SECONDS.get(), TimeUnit.SECONDS)
                .thenApply(response -> parseResponse(response.body()))
                .thenAccept(reply -> server.execute(() -> {
                    player.sendSystemMessage(
                        Component.literal("[").append(entityName).append("]: " + reply)
                    );
                    ConversationMemory.addExchange(npcId, playerName,
                        playerName + ": " + message, npcName + ": " + reply);
                }))
                .exceptionally(ex -> {
                    DragonTweaks.LOGGER.warn("[OllamaClient] LLM request failed: {}", ex.getMessage());
                    server.execute(() -> sendFallback(player, entityName));
                    return null;
                });
        } catch (Exception e) {
            DragonTweaks.LOGGER.error("[OllamaClient] Failed to dispatch async request: {}", e.getMessage());
            server.execute(() -> sendFallback(player, entityName));
        }
    }
}
