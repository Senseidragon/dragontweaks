Compiled from "ICustomConfigurationTask.java"
public interface net.neoforged.neoforge.network.configuration.ICustomConfigurationTask extends net.minecraft.server.network.ConfigurationTask {
  public abstract void run(java.util.function.Consumer<net.minecraft.network.protocol.common.custom.CustomPacketPayload>);
  public default void start(java.util.function.Consumer<net.minecraft.network.protocol.Packet<?>>);
}
