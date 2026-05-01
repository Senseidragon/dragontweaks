Compiled from "DataMapValueMerger.java"
public interface net.neoforged.neoforge.registries.datamaps.DataMapValueMerger<R, T> {
  public abstract T merge(net.minecraft.core.Registry<R>, com.mojang.datafixers.util.Either<net.minecraft.tags.TagKey<R>, net.minecraft.resources.ResourceKey<R>>, T, com.mojang.datafixers.util.Either<net.minecraft.tags.TagKey<R>, net.minecraft.resources.ResourceKey<R>>, T);
  public static <T, R> net.neoforged.neoforge.registries.datamaps.DataMapValueMerger<R, T> defaultMerger();
  public static <T, R> net.neoforged.neoforge.registries.datamaps.DataMapValueMerger<R, java.util.List<T>> listMerger();
  public static <T, R> net.neoforged.neoforge.registries.datamaps.DataMapValueMerger<R, java.util.Set<T>> setMerger();
  public static <K, V, R> net.neoforged.neoforge.registries.datamaps.DataMapValueMerger<R, java.util.Map<K, V>> mapMerger();
}
