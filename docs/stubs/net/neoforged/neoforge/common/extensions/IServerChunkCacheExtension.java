Compiled from "IServerChunkCacheExtension.java"
public interface net.neoforged.neoforge.common.extensions.IServerChunkCacheExtension {
  public default net.minecraft.server.level.ServerChunkCache self();
  public default void broadcastAndSend(net.minecraft.world.entity.Entity, net.minecraft.network.protocol.common.custom.CustomPacketPayload);
  public default void broadcast(net.minecraft.world.entity.Entity, net.minecraft.network.protocol.common.custom.CustomPacketPayload);
}
