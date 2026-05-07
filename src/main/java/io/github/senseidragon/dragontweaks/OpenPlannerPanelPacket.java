package io.github.senseidragon.dragontweaks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record OpenPlannerPanelPacket(PlannerPanelPayload payload) implements CustomPacketPayload {

    public static final Type<OpenPlannerPanelPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(DragonTweaks.MODID, "open_planner_panel"));

    public static final StreamCodec<FriendlyByteBuf, OpenPlannerPanelPacket> STREAM_CODEC =
            StreamCodec.of(OpenPlannerPanelPacket::encode, OpenPlannerPanelPacket::decode);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // ---- Serialization ----

    private static void encode(FriendlyByteBuf buf, OpenPlannerPanelPacket packet) {
        PlannerPanelPayload p = packet.payload();

        buf.writeVarInt(p.getMode().ordinal());

        PlannerPanelPayload.WorkerHeader wh = p.getWorkerHeader();
        buf.writeBoolean(wh != null);
        if (wh != null) {
            buf.writeVarInt(wh.getWorkersAssigned());
            buf.writeVarInt(wh.getTotalSlots());
        }

        PlannerPanelPayload.BedHeader bh = p.getBedHeader();
        buf.writeBoolean(bh != null);
        if (bh != null) {
            buf.writeVarInt(bh.getOccupied());
            buf.writeVarInt(bh.getCapacity());
        }

        List<PlannerPanelPayload.Recommendation> recs = p.getRecommendations();
        buf.writeVarInt(recs.size());
        for (PlannerPanelPayload.Recommendation r : recs) {
            buf.writeUtf(r.getTargetId());
            buf.writeUtf(r.getTargetDisplayName());
            buf.writeVarInt(r.getPriority().ordinal());
            buf.writeBoolean(r.getImmediateBlocker() != null);
            if (r.getImmediateBlocker() != null) buf.writeUtf(r.getImmediateBlocker());
            buf.writeBoolean(r.getResearchPrereqName() != null);
            if (r.getResearchPrereqName() != null) buf.writeUtf(r.getResearchPrereqName());
            buf.writeVarInt(r.getWorkerSlotsNeeded());
            buf.writeVarInt(r.getBedDelta());
            buf.writeVarInt(r.getStepsRemaining());
            buf.writeBoolean(r.isInProgress());
        }

        PlannerPanelPayload.GoalResult gr = p.getGoalResult();
        buf.writeBoolean(gr != null);
        if (gr != null) encodeGoalResult(buf, gr);
    }

    private static void encodeGoalResult(FriendlyByteBuf buf, PlannerPanelPayload.GoalResult gr) {
        buf.writeUtf(gr.getInput());

        List<PlannerDependencyRegistry.BuildingNode> exact = gr.getExactMatches();
        buf.writeVarInt(exact.size());
        for (PlannerDependencyRegistry.BuildingNode n : exact) {
            buf.writeUtf(n.id);
            buf.writeUtf(n.displayName);
        }

        List<PlannerDependencyRegistry.BuildingNode> suggestions = gr.getSuggestions();
        buf.writeVarInt(suggestions.size());
        for (PlannerDependencyRegistry.BuildingNode n : suggestions) {
            buf.writeUtf(n.id);
            buf.writeUtf(n.displayName);
        }

        List<PlannerPanelPayload.ChainStep> chain = gr.getChain();
        buf.writeBoolean(chain != null);
        if (chain != null) {
            buf.writeVarInt(chain.size());
            for (PlannerPanelPayload.ChainStep cs : chain) {
                encodeStep(buf, cs.getStep());
                buf.writeBoolean(cs.isCompleted());
                buf.writeBoolean(cs.isFirstIncomplete());
            }
        }

        PlannerPanelPayload.CostEstimate ce = gr.getCostEstimate();
        buf.writeBoolean(ce != null);
        if (ce != null) {
            buf.writeVarInt(ce.getStepsRemaining());
            List<String> research = ce.getResearchNeeded();
            buf.writeVarInt(research.size());
            for (String r : research) buf.writeUtf(r);
        }

        List<PlannerPanelPayload.MaterialEntry> mats = gr.getMaterials();
        buf.writeVarInt(mats.size());
        for (PlannerPanelPayload.MaterialEntry m : mats) {
            buf.writeUtf(m.getItemDisplayName());
            buf.writeVarInt(m.getRequired());
            buf.writeVarInt(m.getInWarehouse());
            buf.writeVarInt(m.getDeficit());
        }

        buf.writeBoolean(gr.hasWarehouse());
    }

    private static void encodeStep(FriendlyByteBuf buf, PlannerDependencyRegistry.DependencyStep step) {
        buf.writeVarInt(step.stepType.ordinal());
        buf.writeUtf(step.id);
        buf.writeUtf(step.displayName);
        buf.writeVarInt(step.minTotalLevel);
        buf.writeVarInt(step.universityLevelRequired);
    }

    // ---- Deserialization ----

    private static OpenPlannerPanelPacket decode(FriendlyByteBuf buf) {
        PlannerPanelPayload.Mode mode = PlannerPanelPayload.Mode.values()[buf.readVarInt()];

        PlannerPanelPayload.WorkerHeader wh = null;
        if (buf.readBoolean()) {
            wh = new PlannerPanelPayload.WorkerHeader(buf.readVarInt(), buf.readVarInt());
        }

        PlannerPanelPayload.BedHeader bh = null;
        if (buf.readBoolean()) {
            bh = new PlannerPanelPayload.BedHeader(buf.readVarInt(), buf.readVarInt());
        }

        PlannerPanelPayload.Priority[] priorities = PlannerPanelPayload.Priority.values();
        int recCount = buf.readVarInt();
        List<PlannerPanelPayload.Recommendation> recs = new ArrayList<>(recCount);
        for (int i = 0; i < recCount; i++) {
            String targetId = buf.readUtf();
            String targetDisplayName = buf.readUtf();
            PlannerPanelPayload.Priority priority = priorities[buf.readVarInt()];
            String immediateBlocker = buf.readBoolean() ? buf.readUtf() : null;
            String researchPrereqName = buf.readBoolean() ? buf.readUtf() : null;
            int workerSlotsNeeded = buf.readVarInt();
            int bedDelta = buf.readVarInt();
            int stepsRemaining = buf.readVarInt();
            boolean inProgress = buf.readBoolean();
            recs.add(new PlannerPanelPayload.Recommendation(
                    targetId, targetDisplayName, priority, immediateBlocker, researchPrereqName,
                    workerSlotsNeeded, bedDelta, stepsRemaining, inProgress));
        }

        PlannerPanelPayload.GoalResult gr = null;
        if (buf.readBoolean()) {
            gr = decodeGoalResult(buf);
        }

        return new OpenPlannerPanelPacket(PlannerPanelPayload.fromNetwork(mode, wh, bh, recs, gr));
    }

    private static PlannerPanelPayload.GoalResult decodeGoalResult(FriendlyByteBuf buf) {
        String input = buf.readUtf();

        int exactCount = buf.readVarInt();
        List<PlannerDependencyRegistry.BuildingNode> exact = new ArrayList<>(exactCount);
        for (int i = 0; i < exactCount; i++) {
            String id = buf.readUtf();
            String dn = buf.readUtf();
            exact.add(new PlannerDependencyRegistry.BuildingNode(
                    id, dn, Collections.emptyList(), null, 0,
                    Collections.emptyList(), Collections.emptyList()));
        }

        int sugCount = buf.readVarInt();
        List<PlannerDependencyRegistry.BuildingNode> suggestions = new ArrayList<>(sugCount);
        for (int i = 0; i < sugCount; i++) {
            String id = buf.readUtf();
            String dn = buf.readUtf();
            suggestions.add(new PlannerDependencyRegistry.BuildingNode(
                    id, dn, Collections.emptyList(), null, 0,
                    Collections.emptyList(), Collections.emptyList()));
        }

        List<PlannerPanelPayload.ChainStep> chain = null;
        if (buf.readBoolean()) {
            int chainCount = buf.readVarInt();
            chain = new ArrayList<>(chainCount);
            for (int i = 0; i < chainCount; i++) {
                PlannerDependencyRegistry.DependencyStep step = decodeStep(buf);
                boolean completed = buf.readBoolean();
                boolean firstIncomplete = buf.readBoolean();
                chain.add(new PlannerPanelPayload.ChainStep(step, completed, firstIncomplete));
            }
        }

        PlannerPanelPayload.CostEstimate costEstimate = null;
        if (buf.readBoolean()) {
            int stepsRemaining = buf.readVarInt();
            int researchCount = buf.readVarInt();
            List<String> researchNeeded = new ArrayList<>(researchCount);
            for (int i = 0; i < researchCount; i++) researchNeeded.add(buf.readUtf());
            costEstimate = new PlannerPanelPayload.CostEstimate(stepsRemaining, researchNeeded);
        }

        int matCount = buf.readVarInt();
        List<PlannerPanelPayload.MaterialEntry> materials = new ArrayList<>(matCount);
        for (int i = 0; i < matCount; i++) {
            String displayName = buf.readUtf();
            int required = buf.readVarInt();
            int inWarehouse = buf.readVarInt();
            int deficit = buf.readVarInt();
            materials.add(new PlannerPanelPayload.MaterialEntry(displayName, required, inWarehouse, deficit));
        }

        boolean hasWarehouse = buf.readBoolean();

        return new PlannerPanelPayload.GoalResult(
                input, exact, suggestions, chain, costEstimate, materials, hasWarehouse);
    }

    private static PlannerDependencyRegistry.DependencyStep decodeStep(FriendlyByteBuf buf) {
        PlannerDependencyRegistry.DependencyStep.StepType stepType =
                PlannerDependencyRegistry.DependencyStep.StepType.values()[buf.readVarInt()];
        String id = buf.readUtf();
        String displayName = buf.readUtf();
        int minTotalLevel = buf.readVarInt();
        int universityLevelRequired = buf.readVarInt();
        switch (stepType) {
            case BUILDING:
                return PlannerDependencyRegistry.DependencyStep.building(id, displayName);
            case BUILDING_PREREQ:
                return PlannerDependencyRegistry.DependencyStep.buildingPrereq(id, displayName, minTotalLevel);
            case RESEARCH:
                return PlannerDependencyRegistry.DependencyStep.research(id, universityLevelRequired);
            default:
                return PlannerDependencyRegistry.DependencyStep.building(id, displayName);
        }
    }
}
