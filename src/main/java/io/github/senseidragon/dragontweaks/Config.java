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
            .defineInRange("npcObservationHostileCooldownSeconds", 5, 5, 300);

    public static final ModConfigSpec.IntValue NPC_OBSERVATION_PASSIVE_COOLDOWN_SECONDS = BUILDER
            .comment("Minimum seconds between passive-entity observations per NPC")
            .defineInRange("npcObservationPassiveCooldownSeconds", 180, 15, 600);

    public static final ModConfigSpec.BooleanValue LLM_ENABLED = BUILDER
            .comment("Enable LLM-backed responses via OpenRouter. If false, all responses use template fallbacks")
            .define("llmEnabled", true);

    public static final ModConfigSpec.ConfigValue<String> LLM_ENDPOINT = BUILDER
            .comment("OpenRouter API endpoint for LLM-backed responses")
            .define("llmEndpoint", "https://openrouter.ai/api/v1/chat/completions");

    public static final ModConfigSpec.ConfigValue<String> LLM_MODEL = BUILDER
            .comment("OpenRouter model to use for LLM responses")
            .define("llmModel", ModelConfigLoader.getModel());

    public static final ModConfigSpec.IntValue LLM_TIMEOUT_SECONDS = BUILDER
            .comment("Seconds to wait for an LLM response before falling back to templates")
            .defineInRange("llmTimeoutSeconds", 90, 5, 180);

    public static final ModConfigSpec.DoubleValue FLAVOR_NPC_GREETING_CHANCE = BUILDER
            .comment("Chance (0.0–1.0) that a flavor NPC fires a greeting when a player enters detection range")
            .defineInRange("flavorNpcGreetingChance", 0.07, 0.0, 1.0);

    public static final ModConfigSpec.IntValue FLAVOR_NPC_GREETING_COOLDOWN_TICKS = BUILDER
            .comment("Per-NPC, per-player cooldown in ticks before a greeting can fire again for the same player")
            .defineInRange("flavorNpcGreetingCooldownTicks", 12000, 1200, 144000);

    public static final ModConfigSpec.IntValue FLAVOR_NPC_WANDER_RADIUS = BUILDER
            .comment("Radius in blocks within which a flavor NPC may wander from its spawn anchor")
            .defineInRange("flavorNpcWanderRadius", 5, 2, 20);

    public static final ModConfigSpec.IntValue ADVISOR_COMMUTE_THRESHOLD = BUILDER
            .comment("Commute distance in blocks above which a citizen's commute is flagged in the Advisor panel")
            .defineInRange("advisorCommuteThreshold", 80, 10, 500);

    public static final ModConfigSpec.DoubleValue ADVISOR_HAPPINESS_THRESHOLD_RED = BUILDER
            .comment("Happiness factor value below which a factor is classified as red (severe)")
            .defineInRange("advisorHappinessThresholdRed", 0.5, 0.0, 1.0);

    public static final ModConfigSpec.DoubleValue ADVISOR_HAPPINESS_THRESHOLD_YELLOW = BUILDER
            .comment("Happiness factor value below which a factor is classified as yellow (mild); must be above red threshold")
            .defineInRange("advisorHappinessThresholdYellow", 0.9, 0.0, 1.0);

    public static final ModConfigSpec.DoubleValue ADVISOR_ENTITY_OFFSET = BUILDER
            .comment("Distance in blocks the Advisor entity maintains as an offset from the player")
            .defineInRange("advisorEntityOffset", 1.8, 0.5, 5.0);

    public static final ModConfigSpec.IntValue ADVISOR_HOTBAR_CHECK_TICKS = BUILDER
            .comment("Ticks between build tool hotbar checks in PRE_COLONY state")
            .defineInRange("advisorHotbarCheckTicks", 40, 10, 200);

    public static final ModConfigSpec.IntValue ADVISOR_BOUNDARY_DETECTION_RANGE = BUILDER
            .comment("Blocks from the colony boundary before the Advisor snaps back to Town Hall")
            .defineInRange("advisorBoundaryDetectionRange", 40, 10, 200);

    public static final ModConfigSpec.IntValue ADVISOR_WHISPER_THRESHOLD = BUILDER
            .comment("Response length in characters at or above which the Advisor uses the whisper delivery pattern")
            .defineInRange("advisorWhisperThreshold", 120, 40, 500);

    public static final ModConfigSpec.BooleanValue ADVISOR_FORCE_PRIVATE = BUILDER
            .comment("Server operator override: force all Advisor responses to private delivery regardless of length")
            .define("advisorForcePrivate", false);

    public static final ModConfigSpec.IntValue ADVISOR_ROOTCAUSE_SUPPRESS_DAYS = BUILDER
            .comment("Colony days to suppress repeat Advisor observations for a citizen whose root cause has not changed")
            .defineInRange("advisorRootcauseSuppressDays", 2, 0, 30);

    public static final ModConfigSpec.IntValue SCOUT_VILLAGE_REPORT_RADIUS = BUILDER
            .comment("Maximum distance in blocks within which a nearby village is reported during PRE_COLONY scouting. Villages beyond this distance are ignored.")
            .defineInRange("scoutVillageReportRadius", 256, 16, 1024);

    static final ModConfigSpec SPEC = BUILDER.build();

    static void onLoad(ModConfigEvent event) {
        DragonTweaks.LOGGER.debug("DragonTweaks config loaded");
    }
}
