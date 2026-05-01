Compiled from "PacketDistributor.java"
public final class net.neoforged.neoforge.network.PacketDistributor {
  public static void sendToServer(net.minecraft.network.protocol.common.custom.CustomPacketPayload, net.minecraft.network.protocol.common.custom.CustomPacketPayload...);
  public static void sendToPlayer(net.minecraft.server.level.ServerPlayer, net.minecraft.network.protocol.common.custom.CustomPacketPayload, net.minecraft.network.protocol.common.custom.CustomPacketPayload...);
  public static void sendToPlayersInDimension(net.minecraft.server.level.ServerLevel, net.minecraft.network.protocol.common.custom.CustomPacketPayload, net.minecraft.network.protocol.common.custom.CustomPacketPayload...);
  public static void sendToPlayersNear(net.minecraft.server.level.ServerLevel, net.minecraft.server.level.ServerPlayer, double, double, double, double, net.minecraft.network.protocol.common.custom.CustomPacketPayload, net.minecraft.network.protocol.common.custom.CustomPacketPayload...);
  public static void sendToAllPlayers(net.minecraft.network.protocol.common.custom.CustomPacketPayload, net.minecraft.network.protocol.common.custom.CustomPacketPayload...);
  public static void sendToPlayersTrackingEntity(net.minecraft.world.entity.Entity, net.minecraft.network.protocol.common.custom.CustomPacketPayload, net.minecraft.network.protocol.common.custom.CustomPacketPayload...);
  public static void sendToPlayersTrackingEntityAndSelf(net.minecraft.world.entity.Entity, net.minecraft.network.protocol.common.custom.CustomPacketPayload, net.minecraft.network.protocol.common.custom.CustomPacketPayload...);
  public static void sendToPlayersTrackingChunk(net.minecraft.server.level.ServerLevel, net.minecraft.world.level.ChunkPos, net.minecraft.network.protocol.common.custom.CustomPacketPayload, net.minecraft.network.protocol.common.custom.CustomPacketPayload...);
}
