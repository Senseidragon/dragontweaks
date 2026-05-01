Compiled from "DataMapValueRemover.java"
public interface net.neoforged.neoforge.registries.datamaps.DataMapValueRemover<R, T> {
  public abstract java.util.Optional<T> remove(T, net.minecraft.core.Registry<R>, com.mojang.datafixers.util.Either<net.minecraft.tags.TagKey<R>, net.minecraft.resources.ResourceKey<R>>, R);
}
