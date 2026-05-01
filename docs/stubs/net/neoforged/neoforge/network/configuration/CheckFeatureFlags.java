Compiled from "CheckFeatureFlags.java"
public final class net.neoforged.neoforge.network.configuration.CheckFeatureFlags extends java.lang.Record implements net.minecraft.server.network.ConfigurationTask {
  public static final net.minecraft.server.network.ConfigurationTask$Type TYPE;
  public net.neoforged.neoforge.network.configuration.CheckFeatureFlags(net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener);
  public void start(java.util.function.Consumer<net.minecraft.network.protocol.Packet<?>>);
  public static void handleClientboundPayload(net.neoforged.neoforge.network.payload.FeatureFlagDataPayload, net.neoforged.neoforge.network.handling.IPayloadContext);
  public static void handleServerboundPayload(net.neoforged.neoforge.network.payload.FeatureFlagAcknowledgePayload, net.neoforged.neoforge.network.handling.IPayloadContext);
  public static boolean handleVanillaServerConnection(net.minecraft.network.protocol.configuration.ClientConfigurationPacketListener);
  public net.minecraft.server.network.ConfigurationTask$Type type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener listener();
}
