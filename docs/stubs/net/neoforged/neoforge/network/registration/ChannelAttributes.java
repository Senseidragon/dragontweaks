Compiled from "ChannelAttributes.java"
public class net.neoforged.neoforge.network.registration.ChannelAttributes {
  public static final io.netty.util.AttributeKey<net.neoforged.neoforge.network.registration.NetworkPayloadSetup> PAYLOAD_SETUP;
  public static final io.netty.util.AttributeKey<java.util.Set<net.minecraft.resources.ResourceLocation>> ADHOC_CHANNELS;
  public static final io.netty.util.AttributeKey<java.util.Map<net.minecraft.network.ConnectionProtocol, java.util.Set<net.minecraft.resources.ResourceLocation>>> COMMON_CHANNELS;
  public static final io.netty.util.AttributeKey<net.neoforged.neoforge.network.connection.ConnectionType> CONNECTION_TYPE;
  public net.neoforged.neoforge.network.registration.ChannelAttributes();
  public static net.neoforged.neoforge.network.registration.NetworkPayloadSetup getPayloadSetup(net.minecraft.network.Connection);
  public static void setPayloadSetup(net.minecraft.network.Connection, net.neoforged.neoforge.network.registration.NetworkPayloadSetup);
  public static net.neoforged.neoforge.network.connection.ConnectionType getConnectionType(net.minecraft.network.Connection);
  public static void setConnectionType(net.minecraft.network.Connection, net.neoforged.neoforge.network.connection.ConnectionType);
  public static java.util.Set<net.minecraft.resources.ResourceLocation> getOrCreateAdHocChannels(net.minecraft.network.Connection);
  public static java.util.Set<net.minecraft.resources.ResourceLocation> getOrCreateCommonChannels(net.minecraft.network.Connection, net.minecraft.network.ConnectionProtocol);
}
