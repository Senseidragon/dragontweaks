Compiled from "DataMapProvider.java"
public abstract class net.neoforged.neoforge.common.data.DataMapProvider implements net.minecraft.data.DataProvider {
  public java.util.concurrent.CompletableFuture<?> run(net.minecraft.data.CachedOutput);
  public <T, R> net.neoforged.neoforge.common.data.DataMapProvider$Builder<T, R> builder(net.neoforged.neoforge.registries.datamaps.DataMapType<R, T>);
  public <T, R, VR extends net.neoforged.neoforge.registries.datamaps.DataMapValueRemover<R, T>> net.neoforged.neoforge.common.data.DataMapProvider$AdvancedBuilder<T, R, VR> builder(net.neoforged.neoforge.registries.datamaps.AdvancedDataMapType<R, T, VR>);
  public java.lang.String getName();
}
