Compiled from "RegisterDataMapTypesEvent.java"
public class net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent(java.util.Map<net.minecraft.resources.ResourceKey<net.minecraft.core.Registry<?>>, java.util.Map<net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.registries.datamaps.DataMapType<?, ?>>>);
  public <T, R> void register(net.neoforged.neoforge.registries.datamaps.DataMapType<R, T>);
}
