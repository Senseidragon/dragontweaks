Compiled from "GenericPacketSplitter.java"
public class net.neoforged.neoforge.network.filters.GenericPacketSplitter extends io.netty.handler.codec.MessageToMessageEncoder<net.minecraft.network.protocol.Packet<?>> implements net.neoforged.neoforge.network.filters.DynamicChannelHandler {
  public static final java.lang.String CHANNEL_HANDLER_NAME;
  public net.neoforged.neoforge.network.filters.GenericPacketSplitter();
  public boolean isNecessary(net.minecraft.network.Connection);
  public static net.neoforged.neoforge.network.filters.GenericPacketSplitter$RemoteCompatibility getRemoteCompatibility(net.minecraft.network.Connection);
  public static boolean isRemoteCompatible(net.minecraft.network.Connection);
  public static int determineMaxPayloadSize(int);
}
