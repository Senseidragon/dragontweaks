package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.buildings.IBuilding;
import com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenHappinessHandler;
import com.minecolonies.api.entity.citizen.happiness.IHappinessModifier;
import com.minecolonies.api.util.constant.HappinessConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ColonyDiagnosticReportGenerator {

    private static final List<String> CANONICAL_FACTORS = List.of(
            HappinessConstants.FOOD,
            HappinessConstants.SLEPTTONIGHT,
            HappinessConstants.HOMELESSNESS,
            HappinessConstants.HEALTH,
            HappinessConstants.UNEMPLOYMENT,
            HappinessConstants.IDLEATJOB,
            HappinessConstants.SECURITY,
            HappinessConstants.SCHOOL,
            HappinessConstants.SOCIAL,
            HappinessConstants.MYSTICAL_SITE
    );

    public static ColonyDiagnosticReport generate(IColony colony) {
        double redThreshold = Config.ADVISOR_HAPPINESS_THRESHOLD_RED.get();
        double yellowThreshold = Config.ADVISOR_HAPPINESS_THRESHOLD_YELLOW.get();
        int commuteThreshold = Config.ADVISOR_COMMUTE_THRESHOLD.get();

        // Colony metadata
        String colonyName = colony.getName();
        int citizenCount = colony.getCitizenManager().getCurrentCitizenCount();
        int housingCap = countBedCapacity(colony);
        int townHallLevel = colony.getServerBuildingManager().hasTownHall()
                ? colony.getServerBuildingManager().getTownHall().getBuildingLevel()
                : 0;
        double overallHappiness = colony.getOverallHappiness();
        int colonyDay = colony.getDay();

        List<ColonyDiagnosticReport.CitizenRecord> citizens =
                buildCitizenRecords(colony, redThreshold, yellowThreshold);
        List<ColonyDiagnosticReport.BuildingRecord> buildings = buildBuildingRecords(colony);
        List<ColonyDiagnosticReport.EnvironmentalFlag> envFlags = collectEnvironmentalFlags(colony);

        // Phase 3 — systemic pattern check (priority order; first match wins)
        double redT = redThreshold;
        int commuteT = commuteThreshold;

        boolean systemicDetected = false;
        ColonyDiagnosticReport.SystemicPattern systemicPattern = null;

        if (!citizens.isEmpty()) {
            boolean allHaveRed = citizens.stream()
                    .allMatch(c -> c.getHappinessFactors().stream()
                            .anyMatch(ColonyDiagnosticReport.HappinessFactor::isRedFlag));

            long clusterCount = citizens.stream()
                    .filter(c -> c.getCommuteDistance() > commuteT)
                    .filter(c -> c.getHappinessFactors().stream()
                            .anyMatch(f -> f.getFactorId().equals(HappinessConstants.HOMELESSNESS) && f.isRedFlag()))
                    .filter(c -> c.getHappinessFactors().stream()
                            .anyMatch(f -> f.getFactorId().equals(HappinessConstants.SLEPTTONIGHT) && f.isRedFlag()))
                    .count();

            if (colonyDay < 3 && allHaveRed) {
                systemicDetected = true;
                systemicPattern = ColonyDiagnosticReport.SystemicPattern.NEWLY_FOUNDED_ALL_RED;
            } else if (allHaveRed) {
                systemicDetected = true;
                systemicPattern = ColonyDiagnosticReport.SystemicPattern.ALL_CITIZENS_RED;
            } else if (clusterCount >= 2) {
                systemicDetected = true;
                systemicPattern = ColonyDiagnosticReport.SystemicPattern.HOUSING_SLEEP_COMMUTE_CLUSTER;
            }
        }

        // Phase 4 — per-citizen diagnosis (skipped when systemic pattern detected)
        ICitizenData targetCitizen = null;
        String worstFactor = null;
        List<String> redFactors = List.of();
        ColonyDiagnosticReport.RootCause rootCause = null;
        int commuteDistance = 0;
        boolean commuteFlagged = false;

        if (!systemicDetected) {
            List<ICitizenData> allCitizens = colony.getCitizenManager().getCitizens();
            targetCitizen = allCitizens.stream()
                    .min(Comparator
                            .<ICitizenData>comparingDouble(c ->
                                    c.getCitizenHappinessHandler().getHappiness(colony, c))
                            .thenComparing(c -> c.getName()))
                    .orElse(null);

            if (targetCitizen != null) {
                ICitizenHappinessHandler handler = targetCitizen.getCitizenHappinessHandler();
                IBuilding workBuilding = targetCitizen.getWorkBuilding();
                IBuilding homeBuilding = targetCitizen.getHomeBuilding();

                if (workBuilding != null && homeBuilding != null) {
                    BlockPos wp = workBuilding.getPosition();
                    BlockPos hp = homeBuilding.getPosition();
                    int dx = wp.getX() - hp.getX();
                    int dz = wp.getZ() - hp.getZ();
                    commuteDistance = (int) Math.sqrt(dx * dx + dz * dz);
                }
                commuteFlagged = commuteDistance > commuteThreshold;

                double lowestValue = Double.MAX_VALUE;
                List<String> reds = new ArrayList<>();

                for (String factorId : CANONICAL_FACTORS) {
                    IHappinessModifier mod = handler.getModifier(factorId);
                    if (mod != null) {
                        double value = mod.getFactor(targetCitizen);
                        if (value < lowestValue) {
                            lowestValue = value;
                            worstFactor = factorId;
                        }
                        if (value < redT) {
                            reds.add(factorId);
                        }
                    }
                }
                redFactors = List.copyOf(reds);
                rootCause = determineRootCause(worstFactor, homeBuilding, workBuilding,
                        commuteDistance, commuteThreshold, reds);
            }
        }

        return new ColonyDiagnosticReport(
                colonyName, citizenCount, housingCap, townHallLevel, overallHappiness,
                citizens, buildings,
                envFlags, systemicDetected, systemicPattern, targetCitizen,
                worstFactor, redFactors, rootCause, commuteDistance, commuteFlagged);
    }

    private static List<ColonyDiagnosticReport.CitizenRecord> buildCitizenRecords(
            IColony colony, double redThreshold, double yellowThreshold) {
        List<ColonyDiagnosticReport.CitizenRecord> records = new ArrayList<>();
        for (ICitizenData citizen : colony.getCitizenManager().getCitizens()) {
            String name = citizen.getName();

            String job = "unemployed";
            if (citizen.getJob() != null) {
                String tag = citizen.getJob().getNameTagDescription();
                if (tag != null && !tag.isBlank()) job = tag;
            }

            IBuilding workBuilding = citizen.getWorkBuilding();
            IBuilding homeBuilding = citizen.getHomeBuilding();
            BlockPos workPos = workBuilding != null ? workBuilding.getPosition() : null;
            BlockPos homePos = homeBuilding != null ? homeBuilding.getPosition() : null;

            int commute = 0;
            if (workPos != null && homePos != null) {
                int dx = workPos.getX() - homePos.getX();
                int dz = workPos.getZ() - homePos.getZ();
                commute = (int) Math.sqrt(dx * dx + dz * dz);
            }

            ICitizenHappinessHandler handler = citizen.getCitizenHappinessHandler();
            List<ColonyDiagnosticReport.HappinessFactor> factors = new ArrayList<>();
            for (String factorId : CANONICAL_FACTORS) {
                IHappinessModifier mod = handler.getModifier(factorId);
                if (mod != null) {
                    double value = mod.getFactor(citizen);
                    double weight = mod.getWeight();
                    boolean red = value < redThreshold;
                    boolean yellow = !red && value < yellowThreshold;
                    factors.add(new ColonyDiagnosticReport.HappinessFactor(factorId, value, weight, red, yellow));
                }
            }

            double happiness = handler.getHappiness(colony, citizen);
            records.add(new ColonyDiagnosticReport.CitizenRecord(
                    name, job, workPos, homePos, commute, factors, happiness));
        }
        return records;
    }

    private static List<ColonyDiagnosticReport.BuildingRecord> buildBuildingRecords(IColony colony) {
        List<ColonyDiagnosticReport.BuildingRecord> records = new ArrayList<>();
        for (IBuilding building : colony.getServerBuildingManager().getBuildings().values()) {
            String type = building.getBuildingType().getTranslationKey();
            int level = building.getBuildingLevel();
            boolean built = building.isBuilt();
            boolean pending = building.isPendingConstruction();
            String worker = building.getAllAssignedCitizen().stream()
                    .findFirst()
                    .map(c -> c.getName())
                    .orElse(null);
            records.add(new ColonyDiagnosticReport.BuildingRecord(type, level, built, pending, worker));
        }
        return records;
    }

    private static List<ColonyDiagnosticReport.EnvironmentalFlag> collectEnvironmentalFlags(IColony colony) {
        List<ColonyDiagnosticReport.EnvironmentalFlag> flags = new ArrayList<>();
        Level world = colony.getWorld();
        if (!world.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) {
            flags.add(ColonyDiagnosticReport.EnvironmentalFlag.DAYLIGHT_CYCLE_DISABLED);
        }
        if (colony.getRaiderManager().isRaided()) {
            flags.add(ColonyDiagnosticReport.EnvironmentalFlag.RAID_ACTIVE);
        }
        if (world.isThundering()) {
            flags.add(ColonyDiagnosticReport.EnvironmentalFlag.THUNDERSTORM);
        }
        return flags;
    }

    private static ColonyDiagnosticReport.RootCause determineRootCause(
            String worstFactor, IBuilding homeBuilding, IBuilding workBuilding,
            int commuteDistance, int commuteThreshold, List<String> redFactors) {
        if (worstFactor == null) return ColonyDiagnosticReport.RootCause.UNKNOWN;

        if (worstFactor.equals(HappinessConstants.SLEPTTONIGHT)) {
            if (commuteDistance > commuteThreshold) return ColonyDiagnosticReport.RootCause.BED_TOO_FAR_FROM_WORK;
            if (redFactors.contains(HappinessConstants.HOMELESSNESS)) return ColonyDiagnosticReport.RootCause.NO_HOME_ASSIGNED;
            return ColonyDiagnosticReport.RootCause.SLEEP_UNKNOWN;
        }
        if (worstFactor.equals(HappinessConstants.HOMELESSNESS)) {
            return ColonyDiagnosticReport.RootCause.NO_RESIDENCE_AVAILABLE;
        }
        if (worstFactor.equals(HappinessConstants.FOOD)) {
            // TODO: split into NO_FOOD_PRODUCTION vs FOOD_SUPPLY_CHAIN once Cook and Restaurant
            // building translation keys are verified against MineColonies sources.
            // Logic: iterate colony buildings, check getTranslationKey() against Cook/Restaurant keys.
            // Until verified, conservatively returning FOOD_SUPPLY_CHAIN for all food failures.
            return ColonyDiagnosticReport.RootCause.FOOD_SUPPLY_CHAIN;
        }
        if (worstFactor.equals(HappinessConstants.UNEMPLOYMENT)) {
            return ColonyDiagnosticReport.RootCause.NO_WORK_BUILDING;
        }
        if (worstFactor.equals(HappinessConstants.IDLEATJOB)) {
            return ColonyDiagnosticReport.RootCause.WORK_BUILDING_BLOCKED;
        }
        if (worstFactor.equals(HappinessConstants.SECURITY)) {
            return ColonyDiagnosticReport.RootCause.NO_GUARD_COVERAGE;
        }
        if (worstFactor.equals(HappinessConstants.HEALTH)) {
            return ColonyDiagnosticReport.RootCause.INJURY_OR_DISEASE;
        }
        if (worstFactor.equals(HappinessConstants.SOCIAL)) {
            return ColonyDiagnosticReport.RootCause.LOW_CITIZEN_COUNT;
        }
        if (worstFactor.equals(HappinessConstants.SCHOOL)) {
            return ColonyDiagnosticReport.RootCause.NO_SCHOOL;
        }
        if (worstFactor.equals(HappinessConstants.MYSTICAL_SITE)) {
            return ColonyDiagnosticReport.RootCause.NO_MYSTICAL_SITE;
        }
        if (commuteDistance > commuteThreshold) {
            return ColonyDiagnosticReport.RootCause.HOME_TOO_FAR_FROM_WORK;
        }
        return ColonyDiagnosticReport.RootCause.UNKNOWN;
    }

    // TODO: Verify translation key substrings ("residence", ".home", "tavern") against
    // MineColonies 1.21.1 sources before relying on these. No per-building bed-count API
    // verified in stubs. Known bed values: Residence=2, Tavern=4.
    private static int countBedCapacity(IColony colony) {
        int beds = 0;
        for (IBuilding building : colony.getServerBuildingManager().getBuildings().values()) {
            if (!building.isBuilt()) continue;
            String key = building.getBuildingType().getTranslationKey().toLowerCase(java.util.Locale.ROOT);
            if (key.contains("residence") || key.contains(".home")) {
                beds += 2;
            } else if (key.contains("tavern")) {
                beds += 4;
            }
        }
        return beds;
    }
}
