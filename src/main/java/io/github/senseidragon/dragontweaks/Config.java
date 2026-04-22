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

    public static final ModConfigSpec.IntValue NPC_AWARENESS_RADIUS = BUILDER
            .comment("Radius in blocks around an NPC within which it can perceive nearby entities")
            .defineInRange("npcAwarenessRadius", 16, 4, 64);

    public static final ModConfigSpec.ConfigValue<String> NPC_AWARENESS_CATEGORY = BUILDER
            .comment("Which entity categories the NPC is aware of: PASSIVE, HOSTILE, or ALL")
            .define("npcAwarenessCategory", "PASSIVE",
                    o -> o instanceof String s && (s.equals("PASSIVE") || s.equals("HOSTILE") || s.equals("ALL")));

    public static final ModConfigSpec.BooleanValue NPC_OBSERVATIONS_ENABLED = BUILDER
            .comment("Enable proactive NPC observations — NPCs comment when new entities appear nearby")
            .define("npcObservationsEnabled", true);

    public static final ModConfigSpec.IntValue NPC_OBSERVATION_HOSTILE_COOLDOWN_SECONDS = BUILDER
            .comment("Minimum seconds between hostile-entity observations per NPC")
            .defineInRange("npcObservationHostileCooldownSeconds", 15, 5, 300);

    public static final ModConfigSpec.IntValue NPC_OBSERVATION_PASSIVE_COOLDOWN_SECONDS = BUILDER
            .comment("Minimum seconds between passive-entity observations per NPC")
            .defineInRange("npcObservationPassiveCooldownSeconds", 60, 15, 600);

    public static final ModConfigSpec.BooleanValue LLM_ENABLED = BUILDER
            .comment("Enable LLM-backed responses via Ollama. If false, all responses use template fallbacks")
            .define("llmEnabled", true);

    public static final ModConfigSpec.ConfigValue<String> LLM_ENDPOINT = BUILDER
            .comment("OpenRouter API endpoint for LLM-backed responses")
            .define("llmEndpoint", "https://openrouter.ai/api/v1/chat/completions");

    public static final ModConfigSpec.ConfigValue<String> LLM_MODEL = BUILDER
            .comment("OpenRouter model to use for LLM responses")
            .define("llmModel", "google/gemma-2-27b-it");

    public static final ModConfigSpec.IntValue LLM_TIMEOUT_SECONDS = BUILDER
            .comment("Seconds to wait for an LLM response before falling back to templates")
            .defineInRange("llmTimeoutSeconds", 90, 5, 180);

    static final ModConfigSpec SPEC = BUILDER.build();

    static void onLoad(ModConfigEvent event) {
        DragonTweaks.LOGGER.debug("DragonTweaks config loaded");
    }
}
