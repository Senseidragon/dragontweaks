package io.github.senseidragon.dragontweaks;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.neoforged.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BlueprintMaterialsLoader {

    private static final Logger LOGGER = LogManager.getLogger();

    public static Map<String, Integer> getMaterials(String structurePack, String blueprintPath, int level) {
        Path file = FMLPaths.GAMEDIR.get()
                .resolve("config")
                .resolve("DragonTweaks")
                .resolve(structurePack)
                .resolve(blueprintPath + level + ".json");

        if (!Files.exists(file)) {
            LOGGER.warn("[DragonTweaks] Blueprint materials file not found: {}", file);
            return Collections.emptyMap();
        }

        try {
            String json = Files.readString(file);
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();
            JsonObject materials = root.getAsJsonObject("materials");
            if (materials == null) {
                LOGGER.warn("[DragonTweaks] No 'materials' field in: {}", file);
                return Collections.emptyMap();
            }
            Map<String, Integer> result = new HashMap<>();
            for (Map.Entry<String, JsonElement> entry : materials.entrySet()) {
                result.put(entry.getKey(), entry.getValue().getAsInt());
            }
            return result;
        } catch (IOException | IllegalStateException | UnsupportedOperationException e) {
            LOGGER.warn("[DragonTweaks] Failed to parse blueprint materials file {}: {}", file, e.getMessage());
            return Collections.emptyMap();
        }
    }
}
