Compiled from "AdvancedDataMapType.java"
public final class net.neoforged.neoforge.registries.datamaps.AdvancedDataMapType<R, T, VR extends net.neoforged.neoforge.registries.datamaps.DataMapValueRemover<R, T>> extends net.neoforged.neoforge.registries.datamaps.DataMapType<R, T> {
  public com.mojang.serialization.Codec<VR> remover();
  public net.neoforged.neoforge.registries.datamaps.DataMapValueMerger<R, T> merger();
  public static <T, R> net.neoforged.neoforge.registries.datamaps.AdvancedDataMapType$Builder<T, R, net.neoforged.neoforge.registries.datamaps.DataMapValueRemover$Default<T, R>> builder(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceKey<net.minecraft.core.Registry<R>>, com.mojang.serialization.Codec<T>);
}
