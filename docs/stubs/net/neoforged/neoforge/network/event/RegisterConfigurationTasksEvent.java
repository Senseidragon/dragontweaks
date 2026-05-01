Compiled from "RegisterConfigurationTasksEvent.java"
public class net.neoforged.neoforge.network.event.RegisterConfigurationTasksEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.network.event.RegisterConfigurationTasksEvent(net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener);
  public void register(net.minecraft.server.network.ConfigurationTask);
  public java.util.Queue<net.minecraft.server.network.ConfigurationTask> getConfigurationTasks();
  public net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener getListener();
}
