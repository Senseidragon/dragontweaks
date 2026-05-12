package io.github.senseidragon.dragontweaks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record RoleSelectionPacket(int citizenId, String selectedRole) implements CustomPacketPayload {

    public static final Type<RoleSelectionPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(DragonTweaks.MODID, "role_selection"));

    public static final StreamCodec<FriendlyByteBuf, RoleSelectionPacket> STREAM_CODEC =
            StreamCodec.of(RoleSelectionPacket::encode, RoleSelectionPacket::decode);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private static void encode(FriendlyByteBuf buf, RoleSelectionPacket p) {
        buf.writeVarInt(p.citizenId());
        buf.writeUtf(p.selectedRole());
    }

    private static RoleSelectionPacket decode(FriendlyByteBuf buf) {
        return new RoleSelectionPacket(buf.readVarInt(), buf.readUtf());
    }

    public static void handleOnServer(RoleSelectionPacket packet, IPayloadContext ctx) {
        if (DragonTweaks.LITE_MODE) return;
        ctx.enqueueWork(() -> {
            if (!(ctx.player() instanceof ServerPlayer player)) return;
            ServerLevel overworld = player.getServer().getLevel(Level.OVERWORLD);
            if (overworld == null) return;
            RoleAssignmentData roleData = RoleAssignmentData.get(overworld);
            if (roleData.isAssigned(packet.citizenId())) {
                player.sendSystemMessage(Component.literal("This citizen already has a role assigned."));
                return;
            }
            roleData.assign(packet.citizenId(), packet.selectedRole(), player.getUUID());
            player.sendSystemMessage(Component.literal("Role assigned: " + packet.selectedRole() + "."));
            if (packet.selectedRole().equalsIgnoreCase("Advisor")) {
                AdvisorStateData advisorData = AdvisorStateData.get(overworld);
                advisorData.setState(player.getUUID(), AdvisorState.COLONY_WITH_CITIZEN);
                advisorData.setAssignedCitizenId(player.getUUID(), packet.citizenId());
                advisorData.setDirty();
            }
        });
    }
}
