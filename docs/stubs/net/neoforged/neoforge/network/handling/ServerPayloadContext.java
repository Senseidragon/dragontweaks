Compiled from "ServerPayloadContext.java"
public final class net.neoforged.neoforge.network.handling.ServerPayloadContext extends java.lang.Record implements net.neoforged.neoforge.network.handling.IPayloadContext {
  public net.neoforged.neoforge.network.handling.ServerPayloadContext(net.minecraft.network.protocol.common.ServerCommonPacketListener, net.minecraft.resources.ResourceLocation);
  public void handle(net.minecraft.network.protocol.common.custom.CustomPacketPayload);
  public java.util.concurrent.CompletableFuture<java.lang.Void> enqueueWork(java.lang.Runnable);
  public <T> java.util.concurrent.CompletableFuture<T> enqueueWork(java.util.function.Supplier<T>);
  public void finishCurrentTask(net.minecraft.server.network.ConfigurationTask$Type);
  public net.minecraft.network.protocol.PacketFlow flow();
  public net.minecraft.server.level.ServerPlayer player();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.network.protocol.common.ServerCommonPacketListener listener();
  public net.minecraft.resources.ResourceLocation payloadId();
  public net.minecraft.world.entity.player.Player player();
  public net.neoforged.neoforge.common.extensions.ICommonPacketListener listener();
}
