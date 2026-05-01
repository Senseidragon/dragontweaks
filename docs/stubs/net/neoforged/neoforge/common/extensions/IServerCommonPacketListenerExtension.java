Compiled from "IServerCommonPacketListenerExtension.java"
public interface net.neoforged.neoforge.common.extensions.IServerCommonPacketListenerExtension extends net.neoforged.neoforge.common.extensions.ICommonPacketListener {
  public default void send(net.minecraft.network.protocol.common.custom.CustomPacketPayload);
  public abstract void send(net.minecraft.network.protocol.Packet<?>, net.minecraft.network.PacketSendListener);
  public default void send(net.minecraft.network.protocol.common.custom.CustomPacketPayload, net.minecraft.network.PacketSendListener);
}
