Compiled from "IPacketFlowExtension.java"
public interface net.neoforged.neoforge.common.extensions.IPacketFlowExtension {
  public default net.minecraft.network.protocol.PacketFlow self();
  public default boolean isClientbound();
  public default boolean isServerbound();
  public default net.neoforged.fml.LogicalSide getReceptionSide();
}
