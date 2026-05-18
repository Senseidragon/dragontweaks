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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
        int commuteThreshold = Config.ADVISOR_COMMUTE_THRESHOLD.get();
        int suppressDays = Config.ADVISOR_ROOTCAUSE_SUPPRESS_DAYS.get();

        // Step 1: Systemic — fires independently, does not suppress per-citizen output
        if (report.isSystemicPatternDetected() && report.getSystemicPattern() != null) {
            String systemicThrottleKey = colonyId + ":systemic:" + report.getSystemicPattern().name() + ":" + colonyDay;
            String systemicPrompt = buildSystemicPrompt(report.getSystemicPattern());
            server.execute(() -> {
                ServerLevel overworld = server.getLevel(Level.OVERWORLD);
                if (overworld == null) return;
                AdvisorThrottleData throttle = AdvisorThrottleData.get(overworld);
                if (throttle.hasFiredToday(systemicThrottleKey, colonyDay)) return;
                ServerPlayer target = findPlayerInColony(server, level, colony);
                if (target == null) return;
                throttle.markFiredToday(systemicThrottleKey, colonyDay);
                UUID advisorId = UUID.nameUUIDFromBytes(
                        ("dragontweaks-advisor-" + colonyId).getBytes(StandardCharsets.UTF_8));
                String timeOfDay = LLMClient.timeOfDay(level.getDayTime());
                String weather = LLMClient.weather(level.isRaining(), level.isThundering());
                LLMClient.observe(server, target, ADVISOR_NAME, ADVISOR_ROLE,
                        timeOfDay, weather, "the colony", systemicPrompt, advisorId);
            });
        }

        // Step 2: Pre-scan pass — filter flagged citizens, sort red-first then alpha, cap at 5
        List<ColonyDiagnosticReport.CitizenRecord> candidates = report.getCitizens().stream()
                .filter(c -> isRedCitizen(c, commuteThreshold) || isYellowCitizen(c, commuteThreshold))
                .sorted(Comparator.comparingInt((ColonyDiagnosticReport.CitizenRecord c) ->
                                isRedCitizen(c, commuteThreshold) ? 0 : 1)
                        .thenComparing(ColonyDiagnosticReport.CitizenRecord::getName))
                .limit(5)
                .collect(Collectors.toList());

        // Step 3: Take top 2
        List<ColonyDiagnosticReport.CitizenRecord> selected = candidates.stream()
                .limit(2)
                .collect(Collectors.toList());

        // Step 4: Per-citizen loop — each citizen gets its own server.execute() block
        // CitizenRecord has no numeric ID; name is used as identifier in throttle keys
        String targetCitizenName = report.getTargetCitizen() != null ? report.getTargetCitizen().getName() : null;

        for (ColonyDiagnosticReport.CitizenRecord citizen : selected) {
            // 4a: Build suppression key using root cause ordinal
            ColonyDiagnosticReport.RootCause citizenRootCause =
                    (targetCitizenName != null && targetCitizenName.equals(citizen.getName()))
                    ? report.getRootCause()
                    : ColonyDiagnosticReport.RootCause.UNKNOWN;
            int rootCauseOrdinal = citizenRootCause != null
                    ? citizenRootCause.ordinal()
                    : ColonyDiagnosticReport.RootCause.UNKNOWN.ordinal();
            String suppressionKey = colonyId + ":" + citizen.getName() + ":rc" + rootCauseOrdinal;

            // 4c: Daily throttle key (checked on main thread below)
            String dailyKey = colonyId + ":" + citizen.getName() + ":" + colonyDay;

            // Build LLM prompt on async thread
            String whatChanged = buildCitizenPrompt(report, citizen);

            server.execute(() -> {
                ServerLevel overworld = server.getLevel(Level.OVERWORLD);
                if (overworld == null) return;
                AdvisorThrottleData throttle = AdvisorThrottleData.get(overworld);

                // 4b: Suppression check
                int suppressedSince = throttle.getSuppressedSinceDay(suppressionKey);
                if (suppressedSince >= 0) {
                    if (colonyDay - suppressedSince < suppressDays) return;
                    throttle.clearSuppression(suppressionKey);
                }

                // Daily throttle check
                if (throttle.hasFiredToday(dailyKey, colonyDay)) return;

                ServerPlayer target = findPlayerInColony(server, level, colony);
                if (target == null) return;

                throttle.markFiredToday(dailyKey, colonyDay);
                throttle.recordSuppression(suppressionKey, colonyDay);

                UUID advisorId = UUID.nameUUIDFromBytes(
                        ("dragontweaks-advisor-" + colonyId).getBytes(StandardCharsets.UTF_8));
                String timeOfDay = LLMClient.timeOfDay(level.getDayTime());
                String weather = LLMClient.weather(level.isRaining(), level.isThundering());
                LLMClient.observe(server, target, ADVISOR_NAME, ADVISOR_ROLE,
                        timeOfDay, weather, "the colony", whatChanged, advisorId);
            });
        }
    }

    private static boolean isRedCitizen(ColonyDiagnosticReport.CitizenRecord c, int commuteThreshold) {
        if (c.getCommuteDistance() > commuteThreshold) return true;
        return c.getHappinessFactors().stream().anyMatch(ColonyDiagnosticReport.HappinessFactor::isRedFlag);
    }

    private static boolean isYellowCitizen(ColonyDiagnosticReport.CitizenRecord c, int commuteThreshold) {
        if (isRedCitizen(c, commuteThreshold)) return false;
        return c.getHappinessFactors().stream().anyMatch(ColonyDiagnosticReport.HappinessFactor::isYellowFlag);
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

    private static String buildCitizenPrompt(ColonyDiagnosticReport report, ColonyDiagnosticReport.CitizenRecord citizen) {
        String citizenName = citizen.getName();
        String worstFactorId = citizen.getHappinessFactors().stream()
                .filter(f -> f.isRedFlag() || f.isYellowFlag())
                .min(Comparator.comparingDouble(ColonyDiagnosticReport.HappinessFactor::getValue))
                .map(ColonyDiagnosticReport.HappinessFactor::getFactorId)
                .orElse(null);
        String factorDesc = describeFactorId(worstFactorId);
        boolean commuteFlagged = citizen.getCommuteDistance() > Config.ADVISOR_COMMUTE_THRESHOLD.get();
        ColonyDiagnosticReport.RootCause cause =
                (report.getTargetCitizen() != null && report.getTargetCitizen().getName().equals(citizenName))
                ? report.getRootCause()
                : null;
        String causeDesc = describeRootCause(cause, commuteFlagged);
        return citizenName + " is a distressed citizen in the colony. Their biggest problem is "
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
