Compiled from "IPayloadContext.java"
public interface net.neoforged.neoforge.network.handling.IPayloadContext {
  public abstract net.neoforged.neoforge.common.extensions.ICommonPacketListener listener();
  public default net.minecraft.network.Connection connection();
  public abstract net.minecraft.world.entity.player.Player player();
  public default void reply(net.minecraft.network.protocol.common.custom.CustomPacketPayload);
  public default void disconnect(net.minecraft.network.chat.Component);
  public abstract java.util.concurrent.CompletableFuture<java.lang.Void> enqueueWork(java.lang.Runnable);
  public abstract <T> java.util.concurrent.CompletableFuture<T> enqueueWork(java.util.function.Supplier<T>);
  public abstract net.minecraft.network.protocol.PacketFlow flow();
  public default net.minecraft.network.ConnectionProtocol protocol();
  public default void handle(net.minecraft.network.protocol.Packet<?>);
  public abstract void handle(net.minecraft.network.protocol.common.custom.CustomPacketPayload);
  public abstract void finishCurrentTask(net.minecraft.server.network.ConfigurationTask$Type);
  public default io.netty.channel.ChannelHandlerContext channelHandlerContext();
}
