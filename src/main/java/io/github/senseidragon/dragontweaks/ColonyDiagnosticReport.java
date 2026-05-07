package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.ICitizenData;
import net.minecraft.core.BlockPos;

import java.util.List;

public class ColonyDiagnosticReport {

    public enum EnvironmentalFlag {
        DAYLIGHT_CYCLE_DISABLED,
        RAID_ACTIVE,
        THUNDERSTORM
    }

    public enum SystemicPattern {
        NEWLY_FOUNDED_ALL_RED,
        ALL_CITIZENS_RED,
        HOUSING_SLEEP_COMMUTE_CLUSTER
    }

    public enum RootCause {
        BED_TOO_FAR_FROM_WORK,
        NO_HOME_ASSIGNED,
        SLEEP_UNKNOWN,
        NO_RESIDENCE_AVAILABLE,
        NO_FOOD_PRODUCTION,
        FOOD_SUPPLY_CHAIN,
        NO_WORK_BUILDING,
        WORK_BUILDING_BLOCKED,
        NO_GUARD_COVERAGE,
        INJURY_OR_DISEASE,
        LOW_CITIZEN_COUNT,
        NO_SCHOOL,
        NO_MYSTICAL_SITE,
        HOME_TOO_FAR_FROM_WORK,
        UNKNOWN
    }

    public static class HappinessFactor {
        private final String factorId;
        private final double value;
        private final double weight;
        private final boolean redFlag;
        private final boolean yellowFlag;

        public HappinessFactor(String factorId, double value, double weight, boolean redFlag, boolean yellowFlag) {
            this.factorId = factorId;
            this.value = value;
            this.weight = weight;
            this.redFlag = redFlag;
            this.yellowFlag = yellowFlag;
        }

        public String getFactorId() { return factorId; }
        public double getValue() { return value; }
        public double getWeight() { return weight; }
        public boolean isRedFlag() { return redFlag; }
        public boolean isYellowFlag() { return yellowFlag; }
    }

    public static class CitizenRecord {
        private final String name;
        private final String job;
        private final BlockPos workBuildingPos;
        private final BlockPos homeBuildingPos;
        private final int commuteDistance;
        private final List<HappinessFactor> happinessFactors;
        private final double overallHappiness;

        public CitizenRecord(String name, String job, BlockPos workBuildingPos, BlockPos homeBuildingPos,
                             int commuteDistance, List<HappinessFactor> happinessFactors, double overallHappiness) {
            this.name = name;
            this.job = job;
            this.workBuildingPos = workBuildingPos;
            this.homeBuildingPos = homeBuildingPos;
            this.commuteDistance = commuteDistance;
            this.happinessFactors = happinessFactors;
            this.overallHappiness = overallHappiness;
        }

        public String getName() { return name; }
        public String getJob() { return job; }
        public BlockPos getWorkBuildingPos() { return workBuildingPos; }
        public BlockPos getHomeBuildingPos() { return homeBuildingPos; }
        public int getCommuteDistance() { return commuteDistance; }
        public List<HappinessFactor> getHappinessFactors() { return happinessFactors; }
        public double getOverallHappiness() { return overallHappiness; }
    }

    public static class BuildingRecord {
        private final String buildingType;
        private final int level;
        private final boolean built;
        private final boolean pendingConstruction;
        private final String assignedWorker;

        public BuildingRecord(String buildingType, int level, boolean built, boolean pendingConstruction, String assignedWorker) {
            this.buildingType = buildingType;
            this.level = level;
            this.built = built;
            this.pendingConstruction = pendingConstruction;
            this.assignedWorker = assignedWorker;
        }

        public String getBuildingType() { return buildingType; }
        public int getLevel() { return level; }
        public boolean isBuilt() { return built; }
        public boolean isPendingConstruction() { return pendingConstruction; }
        public String getAssignedWorker() { return assignedWorker; }
    }

    // Colony metadata
    private final String colonyName;
    private final int citizenCount;
    private final int housingCap;
    private final int townHallLevel;
    private final double overallHappiness;

    // Per-citizen records
    private final List<CitizenRecord> citizens;

    // Building inventory
    private final List<BuildingRecord> buildings;

    // DiagnosisResult fields
    private final List<EnvironmentalFlag> environmentalFlags;
    private final boolean systemicPatternDetected;
    private final SystemicPattern systemicPattern;
    private final ICitizenData targetCitizen;
    private final String worstFactor;
    private final List<String> redFactors;
    private final RootCause rootCause;
    private final int commuteDistance;
    private final boolean commuteFlagged;

    public ColonyDiagnosticReport(
            String colonyName, int citizenCount, int housingCap, int townHallLevel, double overallHappiness,
            List<CitizenRecord> citizens, List<BuildingRecord> buildings,
            List<EnvironmentalFlag> environmentalFlags, boolean systemicPatternDetected,
            SystemicPattern systemicPattern, ICitizenData targetCitizen,
            String worstFactor, List<String> redFactors, RootCause rootCause,
            int commuteDistance, boolean commuteFlagged) {
        this.colonyName = colonyName;
        this.citizenCount = citizenCount;
        this.housingCap = housingCap;
        this.townHallLevel = townHallLevel;
        this.overallHappiness = overallHappiness;
        this.citizens = citizens;
        this.buildings = buildings;
        this.environmentalFlags = environmentalFlags;
        this.systemicPatternDetected = systemicPatternDetected;
        this.systemicPattern = systemicPattern;
        this.targetCitizen = targetCitizen;
        this.worstFactor = worstFactor;
        this.redFactors = redFactors;
        this.rootCause = rootCause;
        this.commuteDistance = commuteDistance;
        this.commuteFlagged = commuteFlagged;
    }

    public String getColonyName() { return colonyName; }
    public int getCitizenCount() { return citizenCount; }
    public int getHousingCap() { return housingCap; }
    public int getTownHallLevel() { return townHallLevel; }
    public double getOverallHappiness() { return overallHappiness; }
    public List<CitizenRecord> getCitizens() { return citizens; }
    public List<BuildingRecord> getBuildings() { return buildings; }
    public List<EnvironmentalFlag> getEnvironmentalFlags() { return environmentalFlags; }
    public boolean isSystemicPatternDetected() { return systemicPatternDetected; }
    public SystemicPattern getSystemicPattern() { return systemicPattern; }
    public ICitizenData getTargetCitizen() { return targetCitizen; }
    public String getWorstFactor() { return worstFactor; }
    public List<String> getRedFactors() { return redFactors; }
    public RootCause getRootCause() { return rootCause; }
    public int getCommuteDistance() { return commuteDistance; }
    public boolean isCommuteFlagged() { return commuteFlagged; }
}
