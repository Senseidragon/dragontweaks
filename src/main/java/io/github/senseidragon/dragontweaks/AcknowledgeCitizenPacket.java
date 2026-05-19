package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.IColonyManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AcknowledgeCitizenPacket(int citizenId) implements CustomPacketPayload {

    public static final Type<AcknowledgeCitizenPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(DragonTweaks.MODID, "acknowledge_citizen"));

    public static final StreamCodec<FriendlyByteBuf, AcknowledgeCitizenPacket> STREAM_CODEC =
            StreamCodec.of(
                    (buf, p) -> buf.writeVarInt(p.citizenId()),
                    buf -> new AcknowledgeCitizenPacket(buf.readVarInt()));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleOnServer(AcknowledgeCitizenPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            if (!ModList.get().isLoaded("minecolonies")) return;
            if (!(ctx.player() instanceof ServerPlayer player)) return;
            if (!(player.level() instanceof ServerLevel serverLevel)) return;
            ServerLevel overworld = player.getServer().overworld();
            for (IColony colony : IColonyManager.getInstance().getColonies(serverLevel)) {
                if (!player.getUUID().equals(colony.getPermissions().getOwner())) continue;
                var citizen = colony.getCitizenManager().getCivilian(packet.citizenId());
                if (citizen == null) continue;
                CitizenAcknowledgmentData.get(overworld).acknowledge(colony.getID(), packet.citizenId());
                String name = NicknameData.resolve(overworld, colony.getID(), packet.citizenId(), citizen.getName());
                player.sendSystemMessage(Component.literal("[" + name + "]: Aye, " + player.getGameProfile().getName() + "."));
                DragonTweaks.LOGGER.info("[AcknowledgeCitizenPacket] player={} acknowledged citizen={} colony={}", player.getGameProfile().getName(), packet.citizenId(), colony.getID());
                break;
            }
        });
    }
}
