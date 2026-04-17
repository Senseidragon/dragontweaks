package io.github.senseidragon.dragontweaks;

import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue ROLE_SLOTS = BUILDER
            .comment("Default number of assistant role slots available per player")
            .defineInRange("roleSlots", 3, 1, 8);

    public static final ModConfigSpec.IntValue COMMAND_PROXIMITY = BUILDER
            .comment("Radius in blocks within which a player's chat is heard by assigned citizens")
            .defineInRange("commandProximity", 10, 4, 32);

    public static final ModConfigSpec.BooleanValue LLM_ENABLED = BUILDER
            .comment("Enable LLM-backed responses via Ollama. If false, all responses use template fallbacks")
            .define("llmEnabled", true);

    public static final ModConfigSpec.ConfigValue<String> LLM_ENDPOINT = BUILDER
            .comment("Ollama API endpoint for LLM-backed responses")
            .define("llmEndpoint", "http://SENSEI:11434/api/generate");

    public static final ModConfigSpec.ConfigValue<String> LLM_MODEL = BUILDER
            .comment("Ollama model to use for LLM responses")
            .define("llmModel", "gemma4:26b");

    public static final ModConfigSpec.IntValue LLM_TIMEOUT_SECONDS = BUILDER
            .comment("Seconds to wait for an LLM response before falling back to templates")
            .defineInRange("llmTimeoutSeconds", 90, 5, 180);

    static final ModConfigSpec SPEC = BUILDER.build();

    static void onLoad(ModConfigEvent event) {
        DragonTweaks.LOGGER.debug("DragonTweaks config loaded");
    }
}
