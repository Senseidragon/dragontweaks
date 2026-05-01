Compiled from "NetworkRegistry.java"
public class net.neoforged.neoforge.network.registration.NetworkRegistry {
  public static final java.util.List<java.lang.Integer> SUPPORTED_COMMON_NETWORKING_VERSIONS;
  public static void setup();
  public static <T extends net.minecraft.network.protocol.common.custom.CustomPacketPayload, B extends net.minecraft.network.FriendlyByteBuf> void register(net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<T>, net.minecraft.network.codec.StreamCodec<? super B, T>, net.neoforged.neoforge.network.handling.IPayloadHandler<T>, java.util.List<net.minecraft.network.ConnectionProtocol>, java.util.Optional<net.minecraft.network.protocol.PacketFlow>, java.lang.String, boolean);
  public static net.minecraft.network.codec.StreamCodec<? super net.minecraft.network.FriendlyByteBuf, ? extends net.minecraft.network.protocol.common.custom.CustomPacketPayload> getCodec(net.minecraft.resources.ResourceLocation, net.minecraft.network.ConnectionProtocol, net.minecraft.network.protocol.PacketFlow);
  public static boolean isModdedPayload(net.minecraft.network.protocol.common.custom.CustomPacketPayload);
  public static void handleModdedPayload(net.minecraft.network.protocol.common.ServerCommonPacketListener, net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket);
  public static void handleModdedPayload(net.minecraft.network.protocol.common.ClientCommonPacketListener, net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket);
  public static void initializeNeoForgeConnection(net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener, java.util.Map<net.minecraft.network.ConnectionProtocol, java.util.Set<net.neoforged.neoforge.network.payload.ModdedNetworkQueryComponent>>);
  public static boolean initializeOtherConnection(net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener);
  public static void checkPacket(net.minecraft.network.protocol.Packet<?>, net.minecraft.network.protocol.common.ServerCommonPacketListener);
  public static void checkPacket(net.minecraft.network.protocol.Packet<?>, net.minecraft.network.protocol.common.ClientCommonPacketListener);
  public static void onNetworkQuery(net.minecraft.network.protocol.configuration.ClientConfigurationPacketListener);
  public static void initializeNeoForgeConnection(net.minecraft.network.protocol.configuration.ClientConfigurationPacketListener, net.neoforged.neoforge.network.registration.NetworkPayloadSetup);
  public static void initializeOtherConnection(net.minecraft.network.protocol.configuration.ClientConfigurationPacketListener);
  public static boolean hasChannel(net.neoforged.neoforge.common.extensions.ICommonPacketListener, net.minecraft.resources.ResourceLocation);
  public static boolean hasChannel(net.minecraft.network.Connection, net.minecraft.network.ConnectionProtocol, net.minecraft.resources.ResourceLocation);
  public static <T extends net.minecraft.network.PacketListener> java.util.List<net.minecraft.network.protocol.Packet<?>> filterGameBundlePackets(io.netty.channel.ChannelHandlerContext, java.lang.Iterable<net.minecraft.network.protocol.Packet<? super T>>);
  public static void configureMockConnection(net.minecraft.network.Connection);
  public static void onMinecraftRegister(net.minecraft.network.Connection, java.util.Set<net.minecraft.resources.ResourceLocation>);
  public static void onMinecraftUnregister(net.minecraft.network.Connection, java.util.Set<net.minecraft.resources.ResourceLocation>);
  public static java.util.Set<net.minecraft.resources.ResourceLocation> getInitialListeningChannels(net.minecraft.network.protocol.PacketFlow);
  public static java.util.Set<net.minecraft.resources.ResourceLocation> getInitialServerUnregisterChannels();
  public static void checkCommonVersion(net.neoforged.neoforge.common.extensions.ICommonPacketListener, net.neoforged.neoforge.network.payload.CommonVersionPayload);
  public static void onCommonRegister(net.neoforged.neoforge.common.extensions.ICommonPacketListener, net.neoforged.neoforge.network.payload.CommonRegisterPayload);
  public static java.util.Set<net.minecraft.resources.ResourceLocation> getCommonPlayChannels(net.minecraft.network.protocol.PacketFlow);
  public static void onConfigurationFinished(net.neoforged.neoforge.common.extensions.ICommonPacketListener);
  public static net.neoforged.neoforge.network.connection.ConnectionType getConnectionType(net.minecraft.network.Connection);
  public static <T> java.util.concurrent.CompletableFuture<T> guard(java.util.concurrent.CompletableFuture<T>, net.minecraft.resources.ResourceLocation);
  public static <T extends net.minecraft.network.PacketListener> void handlePacketUnchecked(net.minecraft.network.protocol.Packet<T>, net.minecraft.network.PacketListener);
}
