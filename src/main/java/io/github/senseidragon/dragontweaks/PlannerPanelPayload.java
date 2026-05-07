package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.buildings.IBuilding;
import com.minecolonies.api.colony.buildings.ModBuildings;
import com.minecolonies.api.colony.workorders.IServerWorkOrder;
import com.minecolonies.api.colony.workorders.WorkOrderType;
import com.minecolonies.api.research.ILocalResearchTree;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.*;

public class PlannerPanelPayload {

    // -------------------------------------------------------------------------
    // Registry ID → MineColonies building holder map
    // Keys match planner_dependencies.json node IDs.
    // DeferredHolder.getId() gives the canonical minecolonies:<path> ResourceLocation
    // used to match against IBuilding.getBuildingType().getRegistryName().
    // TODO: verify in-game that MineColonies assigns these ResourceLocations to each
    // building type. DeferredHolder field names are verified against ModBuildings stub.
    // -------------------------------------------------------------------------
    private static final Map<String, DeferredHolder<?, ?>> BUILDING_HOLDERS;

    static {
        Map<String, DeferredHolder<?, ?>> m = new LinkedHashMap<>();
        m.put("townhall",      ModBuildings.townHall);
        m.put("builders_hut", ModBuildings.builder);
        m.put("residence",    ModBuildings.home);
        m.put("warehouse",    ModBuildings.wareHouse);
        m.put("courier_post", ModBuildings.deliveryman);
        m.put("guard_tower",  ModBuildings.guardTower);
        m.put("tavern",       ModBuildings.tavern);
        m.put("university",   ModBuildings.university);
        m.put("forester",     ModBuildings.lumberjack);
        m.put("sawmill",      ModBuildings.sawmill);
        m.put("fletcher",     ModBuildings.fletcher);
        m.put("miner",        ModBuildings.miner);
        m.put("blacksmith",   ModBuildings.blacksmith);
        m.put("farmer",       ModBuildings.farmer);
        m.put("fisher",       ModBuildings.fisherman);
        m.put("cook",         ModBuildings.cook);
        m.put("school",       ModBuildings.school);
        m.put("library",      ModBuildings.library);
        m.put("hospital",     ModBuildings.hospital);
        m.put("mystical_site", ModBuildings.mysticalSite);
        BUILDING_HOLDERS = Collections.unmodifiableMap(m);
    }

    // -------------------------------------------------------------------------
    // Public data types
    // -------------------------------------------------------------------------

    public enum Mode { SNAPSHOT, GOAL_INPUT }

    public static class WorkerHeader {
        private final int workersAssigned;
        private final int totalSlots;

        WorkerHeader(int workersAssigned, int totalSlots) {
            this.workersAssigned = workersAssigned;
            this.totalSlots = totalSlots;
        }

        public int getWorkersAssigned() { return workersAssigned; }
        public int getTotalSlots()      { return totalSlots; }
    }

    public static class BedHeader {
        private final int occupied;
        private final int capacity;

        BedHeader(int occupied, int capacity) {
            this.occupied = occupied;
            this.capacity = capacity;
        }

        public int getOccupied() { return occupied; }
        public int getCapacity() { return capacity; }
    }

    public enum Priority { CRISIS, NON_CRISIS }

    public static class Recommendation {
        private final String targetId;
        private final String targetDisplayName;
        private final Priority priority;
        private final String immediateBlocker;    // human-readable description of first incomplete step
        private final String researchPrereqName;  // null unless research is the active blocker
        private final int workerSlotsNeeded;       // 0 unless worker availability is the active constraint
        private final int bedDelta;               // 0 unless bed availability is the active constraint
        private final int stepsRemaining;
        private final boolean inProgress;

        Recommendation(String targetId, String targetDisplayName, Priority priority,
                       String immediateBlocker, String researchPrereqName,
                       int workerSlotsNeeded, int bedDelta, int stepsRemaining, boolean inProgress) {
            this.targetId = targetId;
            this.targetDisplayName = targetDisplayName;
            this.priority = priority;
            this.immediateBlocker = immediateBlocker;
            this.researchPrereqName = researchPrereqName;
            this.workerSlotsNeeded = workerSlotsNeeded;
            this.bedDelta = bedDelta;
            this.stepsRemaining = stepsRemaining;
            this.inProgress = inProgress;
        }

        public String getTargetId()           { return targetId; }
        public String getTargetDisplayName()  { return targetDisplayName; }
        public Priority getPriority()         { return priority; }
        public String getImmediateBlocker()   { return immediateBlocker; }
        public String getResearchPrereqName() { return researchPrereqName; }
        public int getWorkerSlotsNeeded()     { return workerSlotsNeeded; }
        public int getBedDelta()              { return bedDelta; }
        public int getStepsRemaining()        { return stepsRemaining; }
        public boolean isInProgress()         { return inProgress; }
    }

    public static class ChainStep {
        private final PlannerDependencyRegistry.DependencyStep step;
        private final boolean completed;
        private final boolean firstIncomplete;

        ChainStep(PlannerDependencyRegistry.DependencyStep step, boolean completed, boolean firstIncomplete) {
            this.step = step;
            this.completed = completed;
            this.firstIncomplete = firstIncomplete;
        }

        public PlannerDependencyRegistry.DependencyStep getStep() { return step; }
        public boolean isCompleted()      { return completed; }
        public boolean isFirstIncomplete(){ return firstIncomplete; }
    }

    public static class CostEstimate {
        private final int stepsRemaining;
        private final List<String> researchNeeded;

        CostEstimate(int stepsRemaining, List<String> researchNeeded) {
            this.stepsRemaining = stepsRemaining;
            this.researchNeeded = Collections.unmodifiableList(researchNeeded);
        }

        public int getStepsRemaining()         { return stepsRemaining; }
        public List<String> getResearchNeeded(){ return researchNeeded; }
    }

    public static class MaterialEntry {
        private final String itemDisplayName;
        private final int required;     // 0 = unverified (per-building material API not yet implemented)
        private final int inWarehouse;
        private final int deficit;      // -1 = unknown when required amount is unverified

        MaterialEntry(String itemDisplayName, int required, int inWarehouse, int deficit) {
            this.itemDisplayName = itemDisplayName;
            this.required = required;
            this.inWarehouse = inWarehouse;
            this.deficit = deficit;
        }

        public String getItemDisplayName() { return itemDisplayName; }
        public int getRequired()           { return required; }
        public int getInWarehouse()        { return inWarehouse; }
        public int getDeficit()            { return deficit; }
    }

    public static class GoalResult {
        private final String input;
        private final List<PlannerDependencyRegistry.BuildingNode> exactMatches;
        private final List<PlannerDependencyRegistry.BuildingNode> suggestions;
        private final List<ChainStep> chain;          // null if no exact match
        private final CostEstimate costEstimate;       // null if no exact match
        private final List<MaterialEntry> materials;
        private final boolean hasWarehouse;

        GoalResult(String input,
                   List<PlannerDependencyRegistry.BuildingNode> exactMatches,
                   List<PlannerDependencyRegistry.BuildingNode> suggestions,
                   List<ChainStep> chain,
                   CostEstimate costEstimate,
                   List<MaterialEntry> materials,
                   boolean hasWarehouse) {
            this.input = input;
            this.exactMatches = exactMatches;
            this.suggestions = suggestions;
            this.chain = chain;
            this.costEstimate = costEstimate;
            this.materials = materials;
            this.hasWarehouse = hasWarehouse;
        }

        public String getInput()                                                     { return input; }
        public List<PlannerDependencyRegistry.BuildingNode> getExactMatches()        { return exactMatches; }
        public List<PlannerDependencyRegistry.BuildingNode> getSuggestions()         { return suggestions; }
        public List<ChainStep> getChain()                                            { return chain; }
        public CostEstimate getCostEstimate()                                        { return costEstimate; }
        public List<MaterialEntry> getMaterials()                                    { return materials; }
        public boolean hasWarehouse()                                                { return hasWarehouse; }
    }

    // -------------------------------------------------------------------------
    // Payload fields
    // -------------------------------------------------------------------------

    private final Mode mode;
    private final WorkerHeader workerHeader;         // non-null in SNAPSHOT
    private final BedHeader bedHeader;               // non-null in SNAPSHOT
    private final List<Recommendation> recommendations; // non-empty in SNAPSHOT
    private final GoalResult goalResult;             // non-null in GOAL_INPUT

    private PlannerPanelPayload(Mode mode, WorkerHeader workerHeader, BedHeader bedHeader,
                                List<Recommendation> recommendations, GoalResult goalResult) {
        this.mode = mode;
        this.workerHeader = workerHeader;
        this.bedHeader = bedHeader;
        this.recommendations = recommendations;
        this.goalResult = goalResult;
    }

    public Mode getMode()                          { return mode; }
    public WorkerHeader getWorkerHeader()          { return workerHeader; }
    public BedHeader getBedHeader()                { return bedHeader; }
    public List<Recommendation> getRecommendations(){ return recommendations; }
    public GoalResult getGoalResult()              { return goalResult; }

    // -------------------------------------------------------------------------
    // Factory
    // -------------------------------------------------------------------------

    public static PlannerPanelPayload build(IColony colony, String goalInput) {
        if (goalInput == null) {
            return buildSnapshot(colony);
        } else {
            return buildGoalInput(colony, goalInput);
        }
    }

    // -------------------------------------------------------------------------
    // Snapshot mode
    // -------------------------------------------------------------------------

    private static PlannerPanelPayload buildSnapshot(IColony colony) {
        ColonyDiagnosticReport report = ColonyDiagnosticCache.getOrGenerate(colony);

        Set<ResourceLocation> builtTypes     = getBuiltBuildingTypes(colony);
        Map<ResourceLocation, Integer> totals = getBuildingTotalLevels(colony);
        ILocalResearchTree researchTree      = colony.getResearchManager().getResearchTree();
        Set<ResourceLocation> inProgressTypes = getInProgressBuildingTypes(colony);

        WorkerHeader workerHeader = buildWorkerHeader(report);
        BedHeader bedHeader = new BedHeader(report.getCitizenCount(), report.getHousingCap());

        List<Recommendation> recommendations = generateRecommendations(
                colony, report, builtTypes, totals, researchTree, inProgressTypes, workerHeader);

        // Crisis first; within same priority, shortest chain first
        recommendations.sort(Comparator
                .<Recommendation, Integer>comparing(r -> r.priority == Priority.CRISIS ? 0 : 1)
                .thenComparingInt(Recommendation::getStepsRemaining));

        return new PlannerPanelPayload(
                Mode.SNAPSHOT,
                workerHeader,
                bedHeader,
                Collections.unmodifiableList(recommendations),
                null);
    }

    // -------------------------------------------------------------------------
    // Goal input mode
    // -------------------------------------------------------------------------

    private static PlannerPanelPayload buildGoalInput(IColony colony, String goalInput) {
        PlannerDependencyRegistry registry = PlannerDependencyRegistry.getInstance();
        PlannerDependencyRegistry.MatchResult matchResult = registry.findMatches(goalInput);

        Set<ResourceLocation> builtTypes     = getBuiltBuildingTypes(colony);
        Map<ResourceLocation, Integer> totals = getBuildingTotalLevels(colony);
        ILocalResearchTree researchTree      = colony.getResearchManager().getResearchTree();
        boolean hasWarehouse = !colony.getServerBuildingManager().getWareHouses().isEmpty();

        List<ChainStep> chain = null;
        CostEstimate costEstimate = null;
        List<MaterialEntry> materials = Collections.emptyList();

        if (!matchResult.exactMatches.isEmpty()) {
            PlannerDependencyRegistry.BuildingNode node = matchResult.exactMatches.get(0);
            List<PlannerDependencyRegistry.DependencyStep> rawChain = registry.getChain(node.id);

            chain = buildChainSteps(rawChain, builtTypes, totals, researchTree);
            costEstimate = buildCostEstimate(chain);
            // TODO: Per-building material requirements lookup is not yet implemented.
            // getMatchingItemStacksInWarehouse(Predicate<ItemStack>) is available on
            // IWareHouse.getTileEntity() and returns List<Tuple<ItemStack, BlockPos>>.
            // Call pattern (once item types are known):
            //   for (IWareHouse wh : colony.getServerBuildingManager().getWareHouses()) {
            //       List<Tuple<ItemStack, BlockPos>> found =
            //           wh.getTileEntity().getMatchingItemStacksInWarehouse(pred);
            //       inWarehouse += found.stream().mapToInt(t -> t.getA().getCount()).sum();
            //   }
            // Player inventory check requires a player parameter not present in build().
            // Until both are resolved, materials list is always empty.
            materials = buildMaterialsList(colony, hasWarehouse);
        }

        GoalResult goalResult = new GoalResult(
                goalInput,
                matchResult.exactMatches,
                matchResult.suggestions,
                chain,
                costEstimate,
                materials,
                hasWarehouse);

        return new PlannerPanelPayload(
                Mode.GOAL_INPUT,
                null,
                null,
                Collections.emptyList(),
                goalResult);
    }

    // -------------------------------------------------------------------------
    // Colony state helpers (verified APIs only)
    // -------------------------------------------------------------------------

    private static Set<ResourceLocation> getBuiltBuildingTypes(IColony colony) {
        Set<ResourceLocation> built = new LinkedHashSet<>();
        for (IBuilding b : colony.getServerBuildingManager().getBuildings().values()) {
            if (b.isBuilt()) {
                built.add(b.getBuildingType().getRegistryName());
            }
        }
        return built;
    }

    private static Map<ResourceLocation, Integer> getBuildingTotalLevels(IColony colony) {
        Map<ResourceLocation, Integer> totals = new LinkedHashMap<>();
        for (IBuilding b : colony.getServerBuildingManager().getBuildings().values()) {
            if (b.isBuilt()) {
                ResourceLocation rl = b.getBuildingType().getRegistryName();
                totals.merge(rl, b.getBuildingLevel(), Integer::sum);
            }
        }
        return totals;
    }

    // Returns the ResourceLocation for each building type that has a claimed BUILD or UPGRADE
    // work order. Work order location → colony building lookup; unplaced buildings produce no entry.
    private static Set<ResourceLocation> getInProgressBuildingTypes(IColony colony) {
        Set<ResourceLocation> inProgress = new LinkedHashSet<>();
        for (IServerWorkOrder wo : colony.getWorkManager().getWorkOrders().values()) {
            if (!wo.isClaimed()) continue;
            WorkOrderType t = wo.getWorkOrderType();
            if (t != WorkOrderType.BUILD && t != WorkOrderType.UPGRADE) continue;
            IBuilding b = colony.getServerBuildingManager().getBuildings().get(wo.getLocation());
            if (b != null) {
                inProgress.add(b.getBuildingType().getRegistryName());
            }
        }
        return inProgress;
    }

    // -------------------------------------------------------------------------
    // Header helpers
    // -------------------------------------------------------------------------

    private static WorkerHeader buildWorkerHeader(ColonyDiagnosticReport report) {
        // workersAssigned: built buildings with at least one citizen assigned.
        // totalSlots: all built buildings (approximate — includes residences and TH which lack worker slots).
        // No verified MineColonies API for exact worker-slot capacity without per-building module queries.
        long assigned = report.getBuildings().stream()
                .filter(b -> b.isBuilt() && b.getAssignedWorker() != null)
                .count();
        long total = report.getBuildings().stream()
                .filter(ColonyDiagnosticReport.BuildingRecord::isBuilt)
                .count();
        return new WorkerHeader((int) assigned, (int) total);
    }

    // -------------------------------------------------------------------------
    // Recommendation generation
    // -------------------------------------------------------------------------

    private static List<Recommendation> generateRecommendations(
            IColony colony,
            ColonyDiagnosticReport report,
            Set<ResourceLocation> builtTypes,
            Map<ResourceLocation, Integer> totalLevels,
            ILocalResearchTree researchTree,
            Set<ResourceLocation> inProgressTypes,
            WorkerHeader workerHeader) {

        PlannerDependencyRegistry registry = PlannerDependencyRegistry.getInstance();
        Set<String> globalRedFactors = collectGlobalRedFactors(report);

        // Collect {buildingId → priority}; highest priority wins on conflict (CRISIS > NON_CRISIS)
        Map<String, Priority> toRecommend = new LinkedHashMap<>();

        // Housing shortage — no beds → crisis
        boolean bedsFull = report.getHousingCap() <= report.getCitizenCount();
        if (bedsFull) {
            toRecommend.put("residence", Priority.CRISIS);
        }

        // Food crisis
        if (globalRedFactors.contains("food")) {
            toRecommend.merge("cook", Priority.CRISIS, (a, b) -> Priority.CRISIS);
            toRecommend.putIfAbsent("farmer", Priority.NON_CRISIS);
        }

        // Housing/sleep factors
        if (globalRedFactors.contains("housing")) {
            toRecommend.merge("residence", Priority.CRISIS, (a, b) -> Priority.CRISIS);
        }
        if (globalRedFactors.contains("slepttonight")) {
            toRecommend.merge("residence", Priority.CRISIS, (a, b) -> Priority.CRISIS);
        }

        // Security
        if (globalRedFactors.contains("security")) {
            Priority p = report.getEnvironmentalFlags()
                    .contains(ColonyDiagnosticReport.EnvironmentalFlag.RAID_ACTIVE)
                    ? Priority.CRISIS : Priority.NON_CRISIS;
            toRecommend.merge("guard_tower", p, (a, b) -> a == Priority.CRISIS ? a : b);
        }

        // Health, school, mystical, social
        if (globalRedFactors.contains("health")) {
            toRecommend.putIfAbsent("hospital", Priority.NON_CRISIS);
        }
        if (globalRedFactors.contains("school")) {
            toRecommend.putIfAbsent("school", Priority.NON_CRISIS);
        }
        if (globalRedFactors.contains("mystical")) {
            toRecommend.putIfAbsent("mystical_site", Priority.NON_CRISIS);
        }
        if (globalRedFactors.contains("social")) {
            toRecommend.putIfAbsent("tavern", Priority.NON_CRISIS);
        }

        // Unemployment — suggest builder if builder's hut is not built
        if (globalRedFactors.contains("unemployment")) {
            ResourceLocation builderRl = resolveRegistryId("builders_hut");
            if (builderRl == null || !builtTypes.contains(builderRl)) {
                toRecommend.merge("builders_hut", Priority.CRISIS, (a, b) -> Priority.CRISIS);
            }
        }

        boolean workersFull = workerHeader.workersAssigned >= workerHeader.totalSlots;

        List<Recommendation> recs = new ArrayList<>();
        for (Map.Entry<String, Priority> entry : toRecommend.entrySet()) {
            String buildingId = entry.getKey();
            Priority priority = entry.getValue();

            List<PlannerDependencyRegistry.DependencyStep> rawChain = registry.getChain(buildingId);
            if (rawChain.isEmpty()) continue;

            List<ChainStep> annotatedChain = buildChainSteps(rawChain, builtTypes, totalLevels, researchTree);
            int stepsRemaining = (int) annotatedChain.stream().filter(s -> !s.completed).count();

            ChainStep firstIncomplete = annotatedChain.stream()
                    .filter(s -> !s.completed)
                    .findFirst()
                    .orElse(null);

            String immediateBlocker = describeStep(firstIncomplete);
            String researchPrereqName = null;
            if (firstIncomplete != null
                    && firstIncomplete.step.stepType
                    == PlannerDependencyRegistry.DependencyStep.StepType.RESEARCH) {
                researchPrereqName = firstIncomplete.step.displayName;
            }

            int workerSlotsNeeded = workersFull ? 1 : 0;
            int bedDelta = (bedsFull && "residence".equals(buildingId)) ? 1 : 0;

            ResourceLocation buildingRl = resolveRegistryId(buildingId);
            boolean inProgress = buildingRl != null && inProgressTypes.contains(buildingRl);

            // Derive display name from the chain's terminal BUILDING step for this id
            String displayName = rawChain.stream()
                    .filter(s -> s.stepType == PlannerDependencyRegistry.DependencyStep.StepType.BUILDING
                            && s.id.equals(buildingId))
                    .map(s -> s.displayName)
                    .findFirst()
                    .orElse(buildingId);

            recs.add(new Recommendation(
                    buildingId, displayName, priority,
                    immediateBlocker, researchPrereqName,
                    workerSlotsNeeded, bedDelta, stepsRemaining, inProgress));
        }

        return recs;
    }

    private static Set<String> collectGlobalRedFactors(ColonyDiagnosticReport report) {
        Set<String> reds = new LinkedHashSet<>();
        for (ColonyDiagnosticReport.CitizenRecord c : report.getCitizens()) {
            for (ColonyDiagnosticReport.HappinessFactor f : c.getHappinessFactors()) {
                if (f.isRedFlag()) reds.add(f.getFactorId());
            }
        }
        return reds;
    }

    // -------------------------------------------------------------------------
    // Chain step helpers
    // -------------------------------------------------------------------------

    private static List<ChainStep> buildChainSteps(
            List<PlannerDependencyRegistry.DependencyStep> steps,
            Set<ResourceLocation> builtTypes,
            Map<ResourceLocation, Integer> totalLevels,
            ILocalResearchTree researchTree) {

        List<ChainStep> result = new ArrayList<>(steps.size());
        boolean firstIncompleteFound = false;

        for (PlannerDependencyRegistry.DependencyStep step : steps) {
            boolean completed = isStepComplete(step, builtTypes, totalLevels, researchTree);
            boolean isFirst = !completed && !firstIncompleteFound;
            if (isFirst) firstIncompleteFound = true;
            result.add(new ChainStep(step, completed, isFirst));
        }
        return result;
    }

    private static boolean isStepComplete(
            PlannerDependencyRegistry.DependencyStep step,
            Set<ResourceLocation> builtTypes,
            Map<ResourceLocation, Integer> totalLevels,
            ILocalResearchTree researchTree) {

        switch (step.stepType) {
            case BUILDING: {
                ResourceLocation rl = resolveRegistryId(step.id);
                return rl != null && builtTypes.contains(rl);
            }
            case BUILDING_PREREQ: {
                ResourceLocation rl = resolveRegistryId(step.id);
                if (rl == null) return false;
                return totalLevels.getOrDefault(rl, 0) >= step.minTotalLevel;
            }
            case RESEARCH: {
                // ILocalResearchTree.isComplete(ResourceLocation) — verified in ILocalResearchTree stub
                ResourceLocation rl = ResourceLocation.tryParse(step.id);
                return rl != null && researchTree.isComplete(rl);
            }
        }
        return false;
    }

    private static ResourceLocation resolveRegistryId(String registryId) {
        DeferredHolder<?, ?> holder = BUILDING_HOLDERS.get(registryId);
        return holder != null ? holder.getId() : null;
    }

    private static String describeStep(ChainStep cs) {
        if (cs == null) return "Ready to build";
        PlannerDependencyRegistry.DependencyStep step = cs.step;
        switch (step.stepType) {
            case BUILDING:
                return "Build " + step.displayName;
            case BUILDING_PREREQ:
                return step.displayName + " must reach total level " + step.minTotalLevel;
            case RESEARCH:
                return "Research: " + step.displayName
                        + (step.universityLevelRequired > 0
                        ? " (requires University L" + step.universityLevelRequired + ")"
                        : "");
        }
        return step.displayName;
    }

    // -------------------------------------------------------------------------
    // Cost estimate and materials
    // -------------------------------------------------------------------------

    private static CostEstimate buildCostEstimate(List<ChainStep> chain) {
        int stepsRemaining = 0;
        List<String> researchNeeded = new ArrayList<>();
        for (ChainStep cs : chain) {
            if (!cs.completed) {
                stepsRemaining++;
                if (cs.step.stepType == PlannerDependencyRegistry.DependencyStep.StepType.RESEARCH) {
                    researchNeeded.add(cs.step.displayName);
                }
            }
        }
        return new CostEstimate(stepsRemaining, researchNeeded);
    }

    // Materials list: empty until per-building material cost API is verified and a player
    // parameter is added to build(). See goal input mode block for the call pattern.
    // IWareHouse.getTileEntity().getMatchingItemStacksInWarehouse(Predicate<ItemStack>)
    // is the verified warehouse stock query — call it per item type once required items are known.
    private static List<MaterialEntry> buildMaterialsList(IColony colony, boolean hasWarehouse) {
        return Collections.emptyList();
    }
}
