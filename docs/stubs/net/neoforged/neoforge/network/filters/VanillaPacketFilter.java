Compiled from "VanillaPacketFilter.java"
public abstract class net.neoforged.neoforge.network.filters.VanillaPacketFilter extends io.netty.handler.codec.MessageToMessageEncoder<net.minecraft.network.protocol.Packet<?>> implements net.neoforged.neoforge.network.filters.DynamicChannelHandler {
  public abstract boolean isNecessary(net.minecraft.network.Connection);
}
