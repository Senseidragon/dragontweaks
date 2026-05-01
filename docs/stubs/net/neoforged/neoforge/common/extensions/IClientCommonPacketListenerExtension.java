Compiled from "IClientCommonPacketListenerExtension.java"
public interface net.neoforged.neoforge.common.extensions.IClientCommonPacketListenerExtension extends net.neoforged.neoforge.common.extensions.ICommonPacketListener {
  public default void send(net.minecraft.network.protocol.common.custom.CustomPacketPayload);
  public default void disconnect(net.minecraft.network.chat.Component);
  public default net.minecraft.util.thread.ReentrantBlockableEventLoop<?> getMainThreadEventLoop();
}
