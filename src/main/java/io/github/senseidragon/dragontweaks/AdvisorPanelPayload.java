package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.IColony;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class AdvisorPanelPayload {

    public enum Severity { RED, YELLOW, HEALTHY }

    private static final List<String> CANONICAL_FACTOR_ORDER = List.of(
            "food", "slepttonight", "housing", "health", "unemployment",
            "idleatjob", "security", "school", "social", "mystical"
    );

    private static final Map<String, String> MODIFIER_TYPE_LABELS = Map.of(
            "food",          "Static",
            "slepttonight",  "TimeBased",
            "housing",       "Static",
            "health",        "Static",
            "unemployment",  "Static",
            "idleatjob",     "ExpirationBased",
            "security",      "Static",
            "school",        "Static",
            "social",        "ExpirationBased",
            "mystical",      "Static"
    );

    // ---- Nested data types ----

    public static class FactorDetail {
        private final String factorId;
        private final double value;
        private final Severity severity;
        private final String modifierTypeLabel;

        public FactorDetail(String factorId, double value, Severity severity, String modifierTypeLabel) {
            this.factorId = factorId;
            this.value = value;
            this.severity = severity;
            this.modifierTypeLabel = modifierTypeLabel;
        }

        public String getFactorId()         { return factorId; }
        public double getValue()            { return value; }
        public Severity getSeverity()       { return severity; }
        public String getModifierTypeLabel(){ return modifierTypeLabel; }
    }

    public static class CitizenEntry {
        // Drives ordering (red tier → yellow tier → healthy tier)
        private final Severity tier;

        // Collapsed-row fields
        private final String name;
        private final String worstFactorId;
        private final double worstFactorValue;
        private final int additionalComplaintsCount;
        private final boolean commuteFlagged;

        // Expanded-row fields
        private final List<FactorDetail> factors;
        private final int commuteDistance;
        private final int commuteThreshold;

        public CitizenEntry(Severity tier, String name, String worstFactorId, double worstFactorValue,
                            int additionalComplaintsCount, boolean commuteFlagged,
                            List<FactorDetail> factors, int commuteDistance, int commuteThreshold) {
            this.tier = tier;
            this.name = name;
            this.worstFactorId = worstFactorId;
            this.worstFactorValue = worstFactorValue;
            this.additionalComplaintsCount = additionalComplaintsCount;
            this.commuteFlagged = commuteFlagged;
            this.factors = factors;
            this.commuteDistance = commuteDistance;
            this.commuteThreshold = commuteThreshold;
        }

        public Severity getTier()                   { return tier; }
        public String getName()                     { return name; }
        public String getWorstFactorId()            { return worstFactorId; }
        public double getWorstFactorValue()         { return worstFactorValue; }
        public int getAdditionalComplaintsCount()   { return additionalComplaintsCount; }
        public boolean isCommuteFlagged()           { return commuteFlagged; }
        public List<FactorDetail> getFactors()      { return factors; }
        public int getCommuteDistance()             { return commuteDistance; }
        public int getCommuteThreshold()            { return commuteThreshold; }
    }

    // ---- Payload fields ----

    private final List<ColonyDiagnosticReport.EnvironmentalFlag> environmentalFlags;
    private final ColonyDiagnosticReport.SystemicPattern systemicPattern; // null when no pattern detected
    private final double overallHappiness;
    private final int citizenCount;
    private final int housingCap;
    private final List<CitizenEntry> citizens;

    private AdvisorPanelPayload(List<ColonyDiagnosticReport.EnvironmentalFlag> environmentalFlags,
                                ColonyDiagnosticReport.SystemicPattern systemicPattern,
                                double overallHappiness, int citizenCount, int housingCap,
                                List<CitizenEntry> citizens) {
        this.environmentalFlags = environmentalFlags;
        this.systemicPattern = systemicPattern;
        this.overallHappiness = overallHappiness;
        this.citizenCount = citizenCount;
        this.housingCap = housingCap;
        this.citizens = citizens;
    }

    public List<ColonyDiagnosticReport.EnvironmentalFlag> getEnvironmentalFlags() { return environmentalFlags; }
    public ColonyDiagnosticReport.SystemicPattern getSystemicPattern()            { return systemicPattern; }
    public double getOverallHappiness()                                           { return overallHappiness; }
    public int getCitizenCount()                                                  { return citizenCount; }
    public int getHousingCap()                                                    { return housingCap; }
    public List<CitizenEntry> getCitizens()                                       { return citizens; }

    // ---- Factory ----

    /** Network deserialization path — bypasses build() and constructs directly from received fields. */
    static AdvisorPanelPayload fromNetwork(List<ColonyDiagnosticReport.EnvironmentalFlag> flags,
                                          ColonyDiagnosticReport.SystemicPattern pattern,
                                          double overallHappiness, int citizenCount, int housingCap,
                                          List<CitizenEntry> citizens) {
        return new AdvisorPanelPayload(flags, pattern, overallHappiness, citizenCount, housingCap, citizens);
    }

    public static AdvisorPanelPayload build(IColony colony) {
        ColonyDiagnosticReport report = ColonyDiagnosticCache.getOrGenerate(colony);

        int commuteThreshold  = Config.ADVISOR_COMMUTE_THRESHOLD.get();
        double redThreshold   = Config.ADVISOR_HAPPINESS_THRESHOLD_RED.get();
        double yellowThreshold= Config.ADVISOR_HAPPINESS_THRESHOLD_YELLOW.get();

        List<CitizenEntry> redTier     = new ArrayList<>();
        List<CitizenEntry> yellowTier  = new ArrayList<>();
        List<CitizenEntry> healthyTier = new ArrayList<>();

        for (ColonyDiagnosticReport.CitizenRecord citizen : report.getCitizens()) {
            CitizenEntry entry = buildCitizenEntry(citizen, commuteThreshold, redThreshold, yellowThreshold);
            switch (entry.getTier()) {
                case RED     -> redTier.add(entry);
                case YELLOW  -> yellowTier.add(entry);
                case HEALTHY -> healthyTier.add(entry);
            }
        }

        Comparator<CitizenEntry> byName = Comparator.comparing(CitizenEntry::getName, String.CASE_INSENSITIVE_ORDER);
        redTier.sort(byName);
        yellowTier.sort(byName);
        healthyTier.sort(byName);

        List<CitizenEntry> ordered = new ArrayList<>(redTier.size() + yellowTier.size() + healthyTier.size());
        ordered.addAll(redTier);
        ordered.addAll(yellowTier);
        ordered.addAll(healthyTier);

        ColonyDiagnosticReport.SystemicPattern pattern = report.isSystemicPatternDetected()
                ? report.getSystemicPattern()
                : null;

        return new AdvisorPanelPayload(
                report.getEnvironmentalFlags(),
                pattern,
                report.getOverallHappiness(),
                report.getCitizenCount(),
                report.getHousingCap(),
                ordered
        );
    }

    // ---- Private helpers ----

    private static CitizenEntry buildCitizenEntry(ColonyDiagnosticReport.CitizenRecord citizen,
                                                   int commuteThreshold,
                                                   double redThreshold, double yellowThreshold) {
        List<ColonyDiagnosticReport.HappinessFactor> raw = citizen.getHappinessFactors();

        // Build factor detail list in canonical order
        List<FactorDetail> factorDetails = new ArrayList<>(CANONICAL_FACTOR_ORDER.size());
        for (String id : CANONICAL_FACTOR_ORDER) {
            ColonyDiagnosticReport.HappinessFactor rawFactor = findFactor(raw, id);
            double value = rawFactor != null ? rawFactor.getValue() : 1.0;
            Severity sev = toSeverity(value, redThreshold, yellowThreshold);
            String label = MODIFIER_TYPE_LABELS.getOrDefault(id, "Static");
            factorDetails.add(new FactorDetail(id, value, sev, label));
        }

        // Worst factor: single lowest value
        FactorDetail worst = factorDetails.stream()
                .min(Comparator.comparingDouble(FactorDetail::getValue))
                .orElse(factorDetails.get(0));

        // Additional complaints: flagged factors beyond the worst one already shown
        long flaggedCount = factorDetails.stream()
                .filter(f -> f.getSeverity() != Severity.HEALTHY)
                .count();
        int additionalComplaints = (int) Math.max(0, flaggedCount - 1);

        boolean commuteFlagged = citizen.getCommuteDistance() > commuteThreshold;

        // Citizen tier per spec: RED if any red factor OR commute flagged; YELLOW if any yellow (no red); else HEALTHY
        boolean anyRed    = factorDetails.stream().anyMatch(f -> f.getSeverity() == Severity.RED);
        boolean anyYellow = factorDetails.stream().anyMatch(f -> f.getSeverity() == Severity.YELLOW);
        Severity tier;
        if (anyRed || commuteFlagged) {
            tier = Severity.RED;
        } else if (anyYellow) {
            tier = Severity.YELLOW;
        } else {
            tier = Severity.HEALTHY;
        }

        return new CitizenEntry(
                tier,
                citizen.getName(),
                worst.getFactorId(),
                worst.getValue(),
                additionalComplaints,
                commuteFlagged,
                factorDetails,
                citizen.getCommuteDistance(),
                commuteThreshold
        );
    }

    private static ColonyDiagnosticReport.HappinessFactor findFactor(
            List<ColonyDiagnosticReport.HappinessFactor> factors, String id) {
        for (ColonyDiagnosticReport.HappinessFactor f : factors) {
            if (id.equals(f.getFactorId())) return f;
        }
        return null;
    }

    private static Severity toSeverity(double value, double redThreshold, double yellowThreshold) {
        if (value < redThreshold)    return Severity.RED;
        if (value < yellowThreshold) return Severity.YELLOW;
        return Severity.HEALTHY;
    }
}
