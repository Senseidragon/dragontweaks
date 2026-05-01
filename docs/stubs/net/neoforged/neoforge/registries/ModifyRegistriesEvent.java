Compiled from "ModifyRegistriesEvent.java"
public class net.neoforged.neoforge.registries.ModifyRegistriesEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public java.lang.Iterable<? extends net.minecraft.core.Registry<?>> getRegistries();
  public <T> net.minecraft.core.Registry<T> getRegistry(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>>);
}
