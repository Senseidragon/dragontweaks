package io.github.senseidragon.dragontweaks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public record OpenAdvisorPanelPacket(AdvisorPanelPayload payload) implements CustomPacketPayload {

    public static final Type<OpenAdvisorPanelPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(DragonTweaks.MODID, "open_advisor_panel"));

    public static final StreamCodec<FriendlyByteBuf, OpenAdvisorPanelPacket> STREAM_CODEC =
            StreamCodec.of(OpenAdvisorPanelPacket::encode, OpenAdvisorPanelPacket::decode);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // ---- Serialization ----

    private static void encode(FriendlyByteBuf buf, OpenAdvisorPanelPacket packet) {
        AdvisorPanelPayload p = packet.payload();

        List<ColonyDiagnosticReport.EnvironmentalFlag> flags = p.getEnvironmentalFlags();
        buf.writeVarInt(flags.size());
        for (ColonyDiagnosticReport.EnvironmentalFlag f : flags) {
            buf.writeVarInt(f.ordinal());
        }

        ColonyDiagnosticReport.SystemicPattern pattern = p.getSystemicPattern();
        buf.writeBoolean(pattern != null);
        if (pattern != null) buf.writeVarInt(pattern.ordinal());

        buf.writeDouble(p.getOverallHappiness());
        buf.writeVarInt(p.getCitizenCount());
        buf.writeVarInt(p.getHousingCap());

        List<AdvisorPanelPayload.CitizenEntry> citizens = p.getCitizens();
        buf.writeVarInt(citizens.size());
        for (AdvisorPanelPayload.CitizenEntry c : citizens) {
            buf.writeVarInt(c.getTier().ordinal());
            buf.writeUtf(c.getName());
            buf.writeUtf(c.getWorstFactorId());
            buf.writeDouble(c.getWorstFactorValue());
            buf.writeVarInt(c.getAdditionalComplaintsCount());
            buf.writeBoolean(c.isCommuteFlagged());
            List<AdvisorPanelPayload.FactorDetail> factors = c.getFactors();
            buf.writeVarInt(factors.size());
            for (AdvisorPanelPayload.FactorDetail fd : factors) {
                buf.writeUtf(fd.getFactorId());
                buf.writeDouble(fd.getValue());
                buf.writeVarInt(fd.getSeverity().ordinal());
                buf.writeUtf(fd.getModifierTypeLabel());
            }
            buf.writeVarInt(c.getCommuteDistance());
            buf.writeVarInt(c.getCommuteThreshold());
        }
    }

    private static OpenAdvisorPanelPacket decode(FriendlyByteBuf buf) {
        ColonyDiagnosticReport.EnvironmentalFlag[] envVals = ColonyDiagnosticReport.EnvironmentalFlag.values();
        int flagCount = buf.readVarInt();
        List<ColonyDiagnosticReport.EnvironmentalFlag> flags = new ArrayList<>(flagCount);
        for (int i = 0; i < flagCount; i++) flags.add(envVals[buf.readVarInt()]);

        ColonyDiagnosticReport.SystemicPattern pattern = null;
        if (buf.readBoolean()) {
            pattern = ColonyDiagnosticReport.SystemicPattern.values()[buf.readVarInt()];
        }

        double overallHappiness = buf.readDouble();
        int citizenCount = buf.readVarInt();
        int housingCap = buf.readVarInt();

        AdvisorPanelPayload.Severity[] sevVals = AdvisorPanelPayload.Severity.values();
        int numCitizens = buf.readVarInt();
        List<AdvisorPanelPayload.CitizenEntry> citizens = new ArrayList<>(numCitizens);
        for (int i = 0; i < numCitizens; i++) {
            AdvisorPanelPayload.Severity tier = sevVals[buf.readVarInt()];
            String name = buf.readUtf();
            String worstFactorId = buf.readUtf();
            double worstFactorValue = buf.readDouble();
            int additionalComplaints = buf.readVarInt();
            boolean commuteFlagged = buf.readBoolean();
            int factorCount = buf.readVarInt();
            List<AdvisorPanelPayload.FactorDetail> factors = new ArrayList<>(factorCount);
            for (int j = 0; j < factorCount; j++) {
                String factorId = buf.readUtf();
                double value = buf.readDouble();
                AdvisorPanelPayload.Severity sev = sevVals[buf.readVarInt()];
                String modLabel = buf.readUtf();
                factors.add(new AdvisorPanelPayload.FactorDetail(factorId, value, sev, modLabel));
            }
            int commuteDistance = buf.readVarInt();
            int commuteThreshold = buf.readVarInt();
            citizens.add(new AdvisorPanelPayload.CitizenEntry(
                    tier, name, worstFactorId, worstFactorValue,
                    additionalComplaints, commuteFlagged, factors, commuteDistance, commuteThreshold));
        }

        return new OpenAdvisorPanelPacket(AdvisorPanelPayload.fromNetwork(
                flags, pattern, overallHappiness, citizenCount, housingCap, citizens));
    }
}
