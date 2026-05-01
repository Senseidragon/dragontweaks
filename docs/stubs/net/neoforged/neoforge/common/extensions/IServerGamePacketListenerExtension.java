Compiled from "IServerGamePacketListenerExtension.java"
public interface net.neoforged.neoforge.common.extensions.IServerGamePacketListenerExtension extends net.neoforged.neoforge.common.extensions.IServerCommonPacketListenerExtension {
  public default void sendBundled(net.minecraft.network.protocol.common.custom.CustomPacketPayload...);
  public default void sendBundled(java.lang.Iterable<net.minecraft.network.protocol.common.custom.CustomPacketPayload>);
}
