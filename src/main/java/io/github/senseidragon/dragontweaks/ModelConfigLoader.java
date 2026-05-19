package io.github.senseidragon.dragontweaks;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public final class ModelConfigLoader {

    private static final String FALLBACK = "google/gemma-4-26b-a4b-it";
    private static final long CACHE_DURATION_MS = 15 * 60 * 1000L; // 15 minutes

    private static volatile String cachedModel = null;
    private static volatile long cacheExpiryMs = 0L;

    private ModelConfigLoader() {}

    public static String getModel() {
        long now = System.currentTimeMillis();
        if (cachedModel != null && now < cacheExpiryMs) {
            return cachedModel;
        }

        Path path = Path.of(System.getProperty("user.dir"), "model_config.json");
        String json;
        try {
            json = Files.readString(path, StandardCharsets.UTF_8);
        } catch (NoSuchFileException e) {
            DragonTweaks.LOGGER.warn("model_config.json not found at {} — using fallback model: {}", path, FALLBACK);
            return FALLBACK;
        } catch (IOException e) {
            DragonTweaks.LOGGER.warn("Failed to read model_config.json: {} — using fallback model: {}", e.getMessage(), FALLBACK);
            return FALLBACK;
        }

        try {
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();
            JsonObject roles = root.getAsJsonObject("roles");
            if (roles == null || roles.isJsonNull() || roles.entrySet().isEmpty()) {
                DragonTweaks.LOGGER.warn("model_config.json has no roles — using fallback model: {}", FALLBACK);
                return FALLBACK;
            }
            JsonObject firstRole = roles.entrySet().iterator().next().getValue().getAsJsonObject();
            JsonArray candidates = firstRole.getAsJsonArray("candidates");
            if (candidates == null || candidates.isJsonNull() || candidates.size() == 0) {
                DragonTweaks.LOGGER.warn("model_config.json has no candidates — using fallback model: {}", FALLBACK);
                return FALLBACK;
            }
            JsonElement modelIdEl = candidates.get(0).getAsJsonObject().get("model_id");
            if (modelIdEl == null || modelIdEl.isJsonNull()) {
                DragonTweaks.LOGGER.warn("model_config.json first candidate has no model_id — using fallback model: {}", FALLBACK);
                return FALLBACK;
            }
            String modelId = modelIdEl.getAsString().trim();
            if (modelId.isEmpty()) {
                DragonTweaks.LOGGER.warn("model_config.json model_id is empty — using fallback model: {}", FALLBACK);
                return FALLBACK;
            }
            cachedModel = modelId;
            cacheExpiryMs = now + CACHE_DURATION_MS;
            DragonTweaks.LOGGER.info("[ModelConfigLoader] LLM model loaded from model_config.json: {}", modelId);
            return modelId;
        } catch (Exception e) {
            DragonTweaks.LOGGER.warn("Failed to parse model_config.json: {} — using fallback model: {}", e.getMessage(), FALLBACK);
            return FALLBACK;
        }
    }
}
