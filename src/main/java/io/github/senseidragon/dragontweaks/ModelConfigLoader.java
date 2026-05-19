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

    private static volatile JsonObject cachedRoles = null;
    private static volatile long cacheExpiryMs = 0L;

    private ModelConfigLoader() {}

    public static String getModel() {
        return getModel("advisory");
    }

    public static String getModel(String role) {
        long now = System.currentTimeMillis();
        JsonObject roles = cachedRoles;
        if (roles == null || now >= cacheExpiryMs) {
            roles = loadRoles(now);
        }
        if (roles == null) return FALLBACK;
        return extractModel(roles, role);
    }

    private static synchronized JsonObject loadRoles(long now) {
        // Double-checked locking
        if (cachedRoles != null && now < cacheExpiryMs) return cachedRoles;

        Path path = Path.of(System.getProperty("user.dir"), "model_config.json");
        String json;
        try {
            json = Files.readString(path, StandardCharsets.UTF_8);
        } catch (NoSuchFileException e) {
            DragonTweaks.LOGGER.warn("model_config.json not found at {} — using fallback model: {}", path, FALLBACK);
            return null;
        } catch (IOException e) {
            DragonTweaks.LOGGER.warn("Failed to read model_config.json: {} — using fallback model: {}", e.getMessage(), FALLBACK);
            return null;
        }

        try {
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();
            JsonObject roles = root.getAsJsonObject("roles");
            if (roles == null || roles.isJsonNull() || roles.entrySet().isEmpty()) {
                DragonTweaks.LOGGER.warn("model_config.json has no roles — using fallback model: {}", FALLBACK);
                return null;
            }
            cachedRoles = roles;
            cacheExpiryMs = now + CACHE_DURATION_MS;
            DragonTweaks.LOGGER.info("[ModelConfigLoader] Role table loaded from model_config.json ({} tiers)", roles.entrySet().size());
            return roles;
        } catch (Exception e) {
            DragonTweaks.LOGGER.warn("Failed to parse model_config.json: {} — using fallback model: {}", e.getMessage(), FALLBACK);
            return null;
        }
    }

    private static String extractModel(JsonObject roles, String role) {
        JsonElement roleEl = roles.get(role);
        if (roleEl == null || roleEl.isJsonNull()) {
            DragonTweaks.LOGGER.warn("[ModelConfigLoader] Role '{}' not found in model_config.json — using fallback: {}", role, FALLBACK);
            return FALLBACK;
        }
        JsonArray candidates = roleEl.getAsJsonObject().getAsJsonArray("candidates");
        if (candidates == null || candidates.isJsonNull() || candidates.size() == 0) {
            DragonTweaks.LOGGER.warn("[ModelConfigLoader] No candidates for role '{}' — using fallback: {}", role, FALLBACK);
            return FALLBACK;
        }
        JsonElement modelIdEl = candidates.get(0).getAsJsonObject().get("model_id");
        if (modelIdEl == null || modelIdEl.isJsonNull()) {
            DragonTweaks.LOGGER.warn("[ModelConfigLoader] No model_id for role '{}' candidate[0] — using fallback: {}", role, FALLBACK);
            return FALLBACK;
        }
        String modelId = modelIdEl.getAsString().trim();
        if (modelId.isEmpty()) {
            DragonTweaks.LOGGER.warn("[ModelConfigLoader] Empty model_id for role '{}' — using fallback: {}", role, FALLBACK);
            return FALLBACK;
        }
        return modelId;
    }
}
