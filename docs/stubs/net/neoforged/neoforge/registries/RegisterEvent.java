Compiled from "RegisterEvent.java"
public class net.neoforged.neoforge.registries.RegisterEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public <T> void register(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>>, net.minecraft.resources.ResourceLocation, java.util.function.Supplier<T>);
  public <T> void register(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>>, java.util.function.Consumer<net.neoforged.neoforge.registries.RegisterEvent$RegisterHelper<T>>);
  public net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<?>> getRegistryKey();
  public net.minecraft.core.Registry<?> getRegistry();
  public <T> net.minecraft.core.Registry<T> getRegistry(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>>);
}
