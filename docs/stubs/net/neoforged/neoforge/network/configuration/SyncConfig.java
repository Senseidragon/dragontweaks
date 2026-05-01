Compiled from "SyncConfig.java"
public final class net.neoforged.neoforge.network.configuration.SyncConfig extends java.lang.Record implements net.neoforged.neoforge.network.configuration.ICustomConfigurationTask {
  public static net.minecraft.server.network.ConfigurationTask$Type TYPE;
  public net.neoforged.neoforge.network.configuration.SyncConfig(net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener);
  public void run(java.util.function.Consumer<net.minecraft.network.protocol.common.custom.CustomPacketPayload>);
  public net.minecraft.server.network.ConfigurationTask$Type type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener listener();
}
