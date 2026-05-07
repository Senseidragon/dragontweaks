package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.IColonyManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AdvisorDiagnosticLoop {

    private static final int INTERVAL_TICKS = 600;
    private static final Component ADVISOR_NAME = Component.literal("Advisor");
    private static final String ADVISOR_ROLE = "advisor";

    private static int tickCount = 0;
    // Both fields are read/written only on the main server thread
    private static final Set<Integer> dirtyColonies = new HashSet<>();
    private static final Map<Integer, Integer> lastRunTick = new HashMap<>();

    public static void markDirty(int colonyId) {
        dirtyColonies.add(colonyId);
    }

    public static void onServerTick(ServerTickEvent.Post event) {
        tickCount++;
        if (!ModList.get().isLoaded("minecolonies")) return;

        MinecraftServer server = event.getServer();

        for (ServerLevel level : server.getAllLevels()) {
            for (IColony colony : IColonyManager.getInstance().getColonies(level)) {
                int colonyId = colony.getID();
                boolean dirty = dirtyColonies.remove(colonyId);
                boolean intervalElapsed = (tickCount - lastRunTick.getOrDefault(colonyId, 0)) >= INTERVAL_TICKS;

                if (!dirty && !intervalElapsed) continue;
                lastRunTick.put(colonyId, tickCount);

                boolean anyPlayerInColony = false;
                for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                    if (player.serverLevel() == level
                            && colony.isCoordInColony(level, player.blockPosition())) {
                        anyPlayerInColony = true;
                        break;
                    }
                }
                if (!anyPlayerInColony) continue;

                final IColony colonyRef = colony;
                final ServerLevel levelRef = level;
                CompletableFuture.runAsync(() -> runCycleAsync(server, levelRef, colonyRef));
            }
        }
    }

    private static void runCycleAsync(MinecraftServer server, ServerLevel level, IColony colony) {
        ColonyDiagnosticReport report;
        try {
            report = ColonyDiagnosticCache.getOrGenerate(colony);
        } catch (Exception ex) {
            DragonTweaks.LOGGER.warn("[AdvisorDiagnosticLoop] Report generation failed for colony {}: {}",
                    colony.getID(), ex.getMessage());
            return;
        }

        int colonyId = colony.getID();
        int colonyDay = colony.getDay();
        double redThreshold = Config.ADVISOR_HAPPINESS_THRESHOLD_RED.get();

        final String throttleKey;
        final String whatChanged;

        if (report.isSystemicPatternDetected() && report.getSystemicPattern() != null) {
            throttleKey = colonyId + ":systemic:" + report.getSystemicPattern().name() + ":" + colonyDay;
            whatChanged = buildSystemicPrompt(report.getSystemicPattern());
        } else {
            if (report.getTargetCitizen() == null) return;

            double targetHappiness = report.getCitizens().stream()
                    .filter(c -> c.getName().equals(report.getTargetCitizen().getName()))
                    .mapToDouble(ColonyDiagnosticReport.CitizenRecord::getOverallHappiness)
                    .findFirst()
                    .orElse(1.0);
            if (targetHappiness >= redThreshold) return;

            int citizenId = report.getTargetCitizen().getId();
            throttleKey = colonyId + ":" + citizenId + ":" + colonyDay;
            whatChanged = buildCitizenPrompt(report);
        }

        server.execute(() -> {
            ServerLevel overworld = server.getLevel(Level.OVERWORLD);
            if (overworld == null) return;

            AdvisorThrottleData throttle = AdvisorThrottleData.get(overworld);
            if (throttle.hasFired(throttleKey)) return;

            ServerPlayer target = findPlayerInColony(server, level, colony);
            if (target == null) return;

            throttle.markFired(throttleKey);

            UUID advisorId = UUID.nameUUIDFromBytes(
                    ("dragontweaks-advisor-" + colony.getID()).getBytes(StandardCharsets.UTF_8));
            String timeOfDay = LLMClient.timeOfDay(level.getDayTime());
            String weather = LLMClient.weather(level.isRaining(), level.isThundering());
            LLMClient.observe(server, target, ADVISOR_NAME, ADVISOR_ROLE,
                    timeOfDay, weather, "the colony", whatChanged, advisorId);
        });
    }

    private static ServerPlayer findPlayerInColony(MinecraftServer server, ServerLevel level, IColony colony) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (player.serverLevel() == level
                    && colony.isCoordInColony(level, player.blockPosition())) {
                return player;
            }
        }
        return null;
    }

    private static String buildSystemicPrompt(ColonyDiagnosticReport.SystemicPattern pattern) {
        return switch (pattern) {
            case NEWLY_FOUNDED_ALL_RED ->
                "the colony was just founded and all citizens are already struggling with basic needs. " +
                "Advise the player to focus on food, beds, and shelter as first priorities.";
            case ALL_CITIZENS_RED ->
                "every citizen in the colony is deeply unhappy simultaneously. Something is seriously wrong " +
                "across the board. Urge the player to investigate urgently.";
            case HOUSING_SLEEP_COMMUTE_CLUSTER ->
                "multiple citizens cannot sleep and are suffering from both housing problems and long commutes. " +
                "Advise the player to check bed assignments and move homes closer to work buildings.";
        };
    }

    private static String buildCitizenPrompt(ColonyDiagnosticReport report) {
        String citizenName = report.getTargetCitizen().getName();
        String factorDesc = describeFactorId(report.getWorstFactor());
        String causeDesc = describeRootCause(report.getRootCause(), report.isCommuteFlagged());
        return citizenName + " is the most distressed citizen in the colony. Their biggest problem is "
                + factorDesc + ". " + causeDesc
                + " Tell the player what you think is happening and what they should check.";
    }

    private static String describeFactorId(String factorId) {
        if (factorId == null) return "an unknown issue";
        return switch (factorId) {
            case "food" -> "food";
            case "slepttonight" -> "sleep";
            case "housing" -> "housing";
            case "health" -> "health";
            case "unemployment" -> "unemployment";
            case "idleatjob" -> "being idle at work";
            case "security" -> "security";
            case "school" -> "education";
            case "social" -> "social isolation";
            case "mystical" -> "lack of mystical connection";
            default -> factorId;
        };
    }

    private static String describeRootCause(ColonyDiagnosticReport.RootCause cause, boolean commuteFlagged) {
        if (cause == null) return "The cause is unclear.";
        return switch (cause) {
            case BED_TOO_FAR_FROM_WORK -> "Their bed is too far from their work building.";
            case NO_HOME_ASSIGNED -> "They have no home building assigned.";
            case SLEEP_UNKNOWN -> "The cause of their sleep problem is unclear.";
            case NO_RESIDENCE_AVAILABLE -> "There is no available Residence for them.";
            case NO_FOOD_PRODUCTION -> "The colony has no food production building.";
            case FOOD_SUPPLY_CHAIN -> "There may be a food supply chain problem.";
            case NO_WORK_BUILDING -> "There is no matching work building available for them.";
            case WORK_BUILDING_BLOCKED -> "Their work building may be blocked or inaccessible.";
            case NO_GUARD_COVERAGE -> "There is no guard coverage near their location.";
            case INJURY_OR_DISEASE -> "They may be injured or ill.";
            case LOW_CITIZEN_COUNT -> "The colony may be too small or citizens too isolated.";
            case NO_SCHOOL -> "There is no School building or they are unassigned.";
            case NO_MYSTICAL_SITE -> "There is no Mystical Site within range.";
            case HOME_TOO_FAR_FROM_WORK -> "Their home is too far from their work building.";
            case UNKNOWN -> "The cause is unknown — the player should inspect manually.";
        };
    }
}
