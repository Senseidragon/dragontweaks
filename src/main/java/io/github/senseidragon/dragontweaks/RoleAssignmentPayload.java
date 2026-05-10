package io.github.senseidragon.dragontweaks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public record RoleAssignmentPayload(
        String citizenName,
        int citizenId,
        int slotsUsed,
        int slotsMax,
        List<String> availableRoles
) implements CustomPacketPayload {

    public static final List<String> AVAILABLE_ROLES = List.of("Ranch Hand", "Scout", "Advisor", "Planner");

    public static final Type<RoleAssignmentPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(DragonTweaks.MODID, "role_assignment"));

    public static final StreamCodec<FriendlyByteBuf, RoleAssignmentPayload> STREAM_CODEC =
            StreamCodec.of(RoleAssignmentPayload::encode, RoleAssignmentPayload::decode);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private static void encode(FriendlyByteBuf buf, RoleAssignmentPayload p) {
        buf.writeUtf(p.citizenName());
        buf.writeVarInt(p.citizenId());
        buf.writeVarInt(p.slotsUsed());
        buf.writeVarInt(p.slotsMax());
        buf.writeVarInt(p.availableRoles().size());
        for (String role : p.availableRoles()) {
            buf.writeUtf(role);
        }
    }

    private static RoleAssignmentPayload decode(FriendlyByteBuf buf) {
        String citizenName = buf.readUtf();
        int citizenId = buf.readVarInt();
        int slotsUsed = buf.readVarInt();
        int slotsMax = buf.readVarInt();
        int count = buf.readVarInt();
        List<String> roles = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            roles.add(buf.readUtf());
        }
        return new RoleAssignmentPayload(citizenName, citizenId, slotsUsed, slotsMax, roles);
    }
}
