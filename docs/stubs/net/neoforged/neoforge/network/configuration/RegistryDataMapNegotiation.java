Compiled from "RegistryDataMapNegotiation.java"
public final class net.neoforged.neoforge.network.configuration.RegistryDataMapNegotiation extends java.lang.Record implements net.neoforged.neoforge.network.configuration.ICustomConfigurationTask {
  public static final net.minecraft.resources.ResourceLocation ID;
  public static final net.minecraft.server.network.ConfigurationTask$Type TYPE;
  public net.neoforged.neoforge.network.configuration.RegistryDataMapNegotiation(net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener);
  public net.minecraft.server.network.ConfigurationTask$Type type();
  public void run(java.util.function.Consumer<net.minecraft.network.protocol.common.custom.CustomPacketPayload>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener listener();
}
