package io.github.senseidragon.dragontweaks;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlannerDependencyRegistry {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String RESOURCE_PATH = "/data/dragontweaks/planner_dependencies.json";

    private static PlannerDependencyRegistry INSTANCE;

    // -------------------------------------------------------------------------
    // Public types
    // -------------------------------------------------------------------------

    public static class BuildingNode {
        public final String id;
        public final String displayName;
        final List<String> aliases;
        final String researchRequired;
        final int universityLevelRequired;
        final List<String> dependsOn;
        final List<BuildingPrereq> buildingPrereqs;

        BuildingNode(String id, String displayName, List<String> aliases,
                     String researchRequired, int universityLevelRequired,
                     List<String> dependsOn, List<BuildingPrereq> buildingPrereqs) {
            this.id = id;
            this.displayName = displayName;
            this.aliases = aliases;
            this.researchRequired = researchRequired;
            this.universityLevelRequired = universityLevelRequired;
            this.dependsOn = dependsOn;
            this.buildingPrereqs = buildingPrereqs;
        }
    }

    public static class DependencyStep {
        public enum StepType { BUILDING, BUILDING_PREREQ, RESEARCH }

        public final StepType stepType;
        public final String id;
        public final String displayName;
        public final int minTotalLevel;
        public final int universityLevelRequired;

        private DependencyStep(StepType stepType, String id, String displayName,
                               int minTotalLevel, int universityLevelRequired) {
            this.stepType = stepType;
            this.id = id;
            this.displayName = displayName;
            this.minTotalLevel = minTotalLevel;
            this.universityLevelRequired = universityLevelRequired;
        }

        static DependencyStep building(String id, String displayName) {
            return new DependencyStep(StepType.BUILDING, id, displayName, 0, 0);
        }

        static DependencyStep buildingPrereq(String id, String displayName, int minTotalLevel) {
            return new DependencyStep(StepType.BUILDING_PREREQ, id, displayName, minTotalLevel, 0);
        }

        static DependencyStep research(String researchId, int universityLevel) {
            return new DependencyStep(StepType.RESEARCH, researchId, researchId, 0, universityLevel);
        }
    }

    public static class MatchResult {
        public final List<BuildingNode> exactMatches;
        public final List<BuildingNode> suggestions;

        MatchResult(List<BuildingNode> exactMatches, List<BuildingNode> suggestions) {
            this.exactMatches = Collections.unmodifiableList(exactMatches);
            this.suggestions = Collections.unmodifiableList(suggestions);
        }
    }

    // -------------------------------------------------------------------------
    // JSON deserialization types (package-private)
    // -------------------------------------------------------------------------

    private static class JsonRoot {
        int version;
        List<JsonBuildingNode> buildings;
    }

    private static class JsonBuildingNode {
        String id;
        @SerializedName("display_name") String displayName;
        List<String> aliases;
        @SerializedName("research_required") String researchRequired;
        @SerializedName("university_level_required") int universityLevelRequired;
        @SerializedName("depends_on") List<String> dependsOn;
        @SerializedName("building_prereqs") List<JsonBuildingPrereq> buildingPrereqs;
        @SerializedName("auto_satisfied") boolean autoSatisfied;
        String notes;
    }

    private static class JsonBuildingPrereq {
        @SerializedName("building_id") String buildingId;
        @SerializedName("min_total_level") int minTotalLevel;
    }

    // -------------------------------------------------------------------------
    // Internal state
    // -------------------------------------------------------------------------

    private final Map<String, BuildingNode> nodeById = new LinkedHashMap<>();
    private final Map<String, List<BuildingPrereq>> prereqsByNodeId = new LinkedHashMap<>();
    private final Map<String, String> aliasToId = new LinkedHashMap<>();
    private final Map<String, List<DependencyStep>> chainCache = new LinkedHashMap<>();
    private final Set<String> autoSatisfiedIds = new LinkedHashSet<>();

    // -------------------------------------------------------------------------
    // Public API
    // -------------------------------------------------------------------------

    public static void load() {
        PlannerDependencyRegistry registry = new PlannerDependencyRegistry();
        registry.loadInternal();
        INSTANCE = registry;
    }

    public static PlannerDependencyRegistry getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("PlannerDependencyRegistry accessed before load()");
        }
        return INSTANCE;
    }

    public List<DependencyStep> getChain(String buildingId) {
        List<DependencyStep> chain = chainCache.get(buildingId);
        return chain != null ? chain : Collections.emptyList();
    }

    /** Returns true if the building is always treated as complete (e.g. townhall). */
    public boolean isAutoSatisfied(String buildingId) {
        return autoSatisfiedIds.contains(buildingId);
    }

    public MatchResult findMatches(String input) {
        String normalized = input.toLowerCase().trim();

        if (aliasToId.containsKey(normalized)) {
            String id = aliasToId.get(normalized);
            BuildingNode node = nodeById.get(id);
            List<BuildingNode> exact = node != null
                    ? Collections.singletonList(node)
                    : Collections.emptyList();
            return new MatchResult(exact, Collections.emptyList());
        }

        List<BuildingNode> suggestions = new ArrayList<>();
        for (BuildingNode node : nodeById.values()) {
            if (node.id.contains(normalized)) {
                suggestions.add(node);
                continue;
            }
            for (String alias : node.aliases) {
                if (alias.contains(normalized)) {
                    suggestions.add(node);
                    break;
                }
            }
        }
        return new MatchResult(Collections.emptyList(), suggestions);
    }

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------

    private void loadInternal() {
        InputStream is = PlannerDependencyRegistry.class.getResourceAsStream(RESOURCE_PATH);
        if (is == null) {
            LOGGER.error("PlannerDependencyRegistry: resource not found at {}", RESOURCE_PATH);
            return;
        }

        JsonRoot root;
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            root = new Gson().fromJson(reader, JsonRoot.class);
        } catch (Exception e) {
            LOGGER.error("PlannerDependencyRegistry: failed to parse JSON", e);
            return;
        }

        if (root == null || root.buildings == null) {
            LOGGER.error("PlannerDependencyRegistry: JSON root or buildings list is null");
            return;
        }

        for (JsonBuildingNode jn : root.buildings) {
            List<String> aliases = jn.aliases != null ? jn.aliases : Collections.emptyList();
            List<String> dependsOn = jn.dependsOn != null ? jn.dependsOn : Collections.emptyList();

            List<BuildingPrereq> prereqs = new ArrayList<>();
            if (jn.buildingPrereqs != null) {
                for (JsonBuildingPrereq jp : jn.buildingPrereqs) {
                    prereqs.add(new BuildingPrereq(jp.buildingId, jp.minTotalLevel));
                }
            }

            BuildingNode node = new BuildingNode(
                    jn.id,
                    jn.displayName,
                    Collections.unmodifiableList(aliases),
                    jn.researchRequired,
                    jn.universityLevelRequired,
                    Collections.unmodifiableList(dependsOn),
                    Collections.unmodifiableList(prereqs)
            );
            nodeById.put(jn.id, node);
            prereqsByNodeId.put(jn.id, prereqs);
            if (jn.autoSatisfied) autoSatisfiedIds.add(jn.id);

            aliasToId.put(jn.id, jn.id);
            for (String alias : aliases) {
                aliasToId.put(alias.toLowerCase(), jn.id);
            }
        }

        buildAllChains();
        LOGGER.info("PlannerDependencyRegistry: loaded {} buildings", nodeById.size());
    }

    private void buildAllChains() {
        for (String id : nodeById.keySet()) {
            Set<String> visited = new LinkedHashSet<>();
            List<DependencyStep> steps = new ArrayList<>();
            resolveChain(id, visited, steps);
            chainCache.put(id, Collections.unmodifiableList(steps));
        }
    }

    private void resolveChain(String targetId, Set<String> visited, List<DependencyStep> steps) {
        if (visited.contains(targetId)) return;
        visited.add(targetId);

        BuildingNode node = nodeById.get(targetId);
        if (node == null) {
            LOGGER.warn("PlannerDependencyRegistry: unknown node id '{}' referenced in depends_on", targetId);
            return;
        }

        // Recurse into structural dependencies first (deepest path first)
        for (String depId : node.dependsOn) {
            resolveChain(depId, visited, steps);
        }

        // For each building prereq: resolve its chain, then annotate the level requirement
        for (BuildingPrereq prereq : node.buildingPrereqs) {
            resolveChain(prereq.buildingId, visited, steps);
            BuildingNode prereqNode = nodeById.get(prereq.buildingId);
            String prereqDisplay = prereqNode != null ? prereqNode.displayName : prereq.buildingId;
            steps.add(DependencyStep.buildingPrereq(prereq.buildingId, prereqDisplay, prereq.minTotalLevel));
        }

        // Research step comes after prereqs, before the building itself
        if (node.researchRequired != null) {
            steps.add(DependencyStep.research(node.researchRequired, node.universityLevelRequired));
        }

        steps.add(DependencyStep.building(node.id, node.displayName));
    }

    // -------------------------------------------------------------------------
    // Internal record
    // -------------------------------------------------------------------------

    static class BuildingPrereq {
        final String buildingId;
        final int minTotalLevel;

        BuildingPrereq(String buildingId, int minTotalLevel) {
            this.buildingId = buildingId;
            this.minTotalLevel = minTotalLevel;
        }
    }
}
