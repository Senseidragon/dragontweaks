Compiled from "DataMapsUpdatedEvent.java"
public class net.neoforged.neoforge.registries.datamaps.DataMapsUpdatedEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.registries.datamaps.DataMapsUpdatedEvent(net.minecraft.core.RegistryAccess, net.minecraft.core.Registry<?>, net.neoforged.neoforge.registries.datamaps.DataMapsUpdatedEvent$UpdateCause);
  public net.minecraft.core.RegistryAccess getRegistries();
  public net.minecraft.core.Registry<?> getRegistry();
  public net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<?>> getRegistryKey();
  public <T> void ifRegistry(net.minecraft.resources.ResourceKey<net.minecraft.core.Registry<T>>, java.util.function.Consumer<net.minecraft.core.Registry<T>>);
  public net.neoforged.neoforge.registries.datamaps.DataMapsUpdatedEvent$UpdateCause getCause();
}
