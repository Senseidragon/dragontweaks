package io.github.senseidragon.dragontweaks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public final class ModelConfigLoader {

    private static final String FALLBACK = "google/gemma-4-26b-a4b-it";

    private ModelConfigLoader() {}

    public static String getModel() {
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

        // Find first "model_id" value in the JSON array without an external library.
        // Expected format: [{"model_id": "some/model", ...}, ...]
        int keyIdx = json.indexOf("\"model_id\"");
        if (keyIdx < 0) {
            DragonTweaks.LOGGER.warn("model_config.json contains no model_id field — using fallback model: {}", FALLBACK);
            return FALLBACK;
        }
        int colon = json.indexOf(':', keyIdx);
        if (colon < 0) {
            DragonTweaks.LOGGER.warn("model_config.json malformed near model_id — using fallback model: {}", FALLBACK);
            return FALLBACK;
        }
        int open = json.indexOf('"', colon + 1);
        int close = json.indexOf('"', open + 1);
        if (open < 0 || close <= open) {
            DragonTweaks.LOGGER.warn("model_config.json model_id value could not be parsed — using fallback model: {}", FALLBACK);
            return FALLBACK;
        }
        String modelId = json.substring(open + 1, close).trim();
        if (modelId.isEmpty()) {
            DragonTweaks.LOGGER.warn("model_config.json model_id is empty — using fallback model: {}", FALLBACK);
            return FALLBACK;
        }
        DragonTweaks.LOGGER.info("[DragonTweaks] LLM model loaded from model_config.json: {}", modelId);
        return modelId;
    }
}
