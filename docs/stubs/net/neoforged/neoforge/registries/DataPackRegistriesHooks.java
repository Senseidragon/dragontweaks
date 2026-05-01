Compiled from "DataPackRegistriesHooks.java"
public final class net.neoforged.neoforge.registries.DataPackRegistriesHooks {
  public static java.util.List<net.minecraft.resources.RegistryDataLoader$RegistryData<?>> grabNetworkableRegistries(java.util.List<net.minecraft.resources.RegistryDataLoader$RegistryData<?>>);
  public static java.util.List<net.minecraft.resources.RegistryDataLoader$RegistryData<?>> getDataPackRegistries();
  public static java.util.stream.Stream<net.minecraft.resources.RegistryDataLoader$RegistryData<?>> getDataPackRegistriesWithDimensions();
  public static java.util.Set<net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<?>>> getSyncedCustomRegistries();
  public static <T> net.minecraft.resources.RegistryDataLoader$RegistryData<T> getSyncedRegistry(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>>);
}
