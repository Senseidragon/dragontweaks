Compiled from "ICommonPacketListener.java"
public interface net.neoforged.neoforge.common.extensions.ICommonPacketListener extends net.minecraft.network.PacketListener {
  public abstract void send(net.minecraft.network.protocol.Packet<?>);
  public abstract void send(net.minecraft.network.protocol.common.custom.CustomPacketPayload);
  public abstract void disconnect(net.minecraft.network.chat.Component);
  public abstract net.minecraft.network.Connection getConnection();
  public abstract net.minecraft.util.thread.ReentrantBlockableEventLoop<?> getMainThreadEventLoop();
  public default boolean hasChannel(net.minecraft.resources.ResourceLocation);
  public default boolean hasChannel(net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<?>);
  public default boolean hasChannel(net.minecraft.network.protocol.common.custom.CustomPacketPayload);
  public abstract net.neoforged.neoforge.network.connection.ConnectionType getConnectionType();
}
