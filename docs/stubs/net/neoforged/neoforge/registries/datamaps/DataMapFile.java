Compiled from "DataMapFile.java"
public final class net.neoforged.neoforge.registries.datamaps.DataMapFile<T, R> extends java.lang.Record {
  public net.neoforged.neoforge.registries.datamaps.DataMapFile(boolean, java.util.Map<com.mojang.datafixers.util.Either<net.minecraft.tags.TagKey<R>, net.minecraft.resources.ResourceKey<R>>, java.util.Optional<net.neoforged.neoforge.common.conditions.WithConditions<net.neoforged.neoforge.registries.datamaps.DataMapEntry<T>>>>, java.util.List<net.neoforged.neoforge.registries.datamaps.DataMapEntry$Removal<T, R>>);
  public static <T, R> com.mojang.serialization.Codec<net.neoforged.neoforge.registries.datamaps.DataMapFile<T, R>> codec(net.minecraft.resources.ResourceKey<net.minecraft.core.Registry<R>>, net.neoforged.neoforge.registries.datamaps.DataMapType<R, T>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public boolean replace();
  public java.util.Map<com.mojang.datafixers.util.Either<net.minecraft.tags.TagKey<R>, net.minecraft.resources.ResourceKey<R>>, java.util.Optional<net.neoforged.neoforge.common.conditions.WithConditions<net.neoforged.neoforge.registries.datamaps.DataMapEntry<T>>>> values();
  public java.util.List<net.neoforged.neoforge.registries.datamaps.DataMapEntry$Removal<T, R>> removals();
}
