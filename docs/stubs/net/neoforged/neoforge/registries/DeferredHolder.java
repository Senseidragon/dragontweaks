Compiled from "DeferredHolder.java"
public class net.neoforged.neoforge.registries.DeferredHolder<R, T extends R> implements net.minecraft.core.Holder<R>, java.util.function.Supplier<T> {
  public static <R, T extends R> net.neoforged.neoforge.registries.DeferredHolder<R, T> create(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<R>>, net.minecraft.resources.ResourceLocation);
  public static <R, T extends R> net.neoforged.neoforge.registries.DeferredHolder<R, T> create(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation);
  public static <R, T extends R> net.neoforged.neoforge.registries.DeferredHolder<R, T> create(net.minecraft.resources.ResourceKey<R>);
  public T value();
  public T get();
  public java.util.Optional<T> asOptional();
  public net.minecraft.resources.ResourceLocation getId();
  public net.minecraft.resources.ResourceKey<R> getKey();
  public boolean equals(java.lang.Object);
  public int hashCode();
  public java.lang.String toString();
  public boolean isBound();
  public boolean is(net.minecraft.resources.ResourceLocation);
  public boolean is(net.minecraft.resources.ResourceKey<R>);
  public boolean is(java.util.function.Predicate<net.minecraft.resources.ResourceKey<R>>);
  public boolean is(net.minecraft.tags.TagKey<R>);
  public boolean is(net.minecraft.core.Holder<R>);
  public <Z> Z getData(net.neoforged.neoforge.registries.datamaps.DataMapType<R, Z>);
  public java.util.stream.Stream<net.minecraft.tags.TagKey<R>> tags();
  public com.mojang.datafixers.util.Either<net.minecraft.resources.ResourceKey<R>, R> unwrap();
  public java.util.Optional<net.minecraft.resources.ResourceKey<R>> unwrapKey();
  public net.minecraft.core.Holder$Kind kind();
  public boolean canSerializeIn(net.minecraft.core.HolderOwner<R>);
  public net.minecraft.core.Holder<R> getDelegate();
}
