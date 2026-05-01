Compiled from "AnyHolderSet.java"
public final class net.neoforged.neoforge.registries.holdersets.AnyHolderSet<T> extends java.lang.Record implements net.neoforged.neoforge.registries.holdersets.ICustomHolderSet<T> {
  public net.neoforged.neoforge.registries.holdersets.AnyHolderSet(net.minecraft.core.HolderLookup$RegistryLookup<T>);
  public net.neoforged.neoforge.registries.holdersets.HolderSetType type();
  public java.util.Iterator<net.minecraft.core.Holder<T>> iterator();
  public java.util.stream.Stream<net.minecraft.core.Holder<T>> stream();
  public int size();
  public com.mojang.datafixers.util.Either<net.minecraft.tags.TagKey<T>, java.util.List<net.minecraft.core.Holder<T>>> unwrap();
  public java.util.Optional<net.minecraft.core.Holder<T>> getRandomElement(net.minecraft.util.RandomSource);
  public net.minecraft.core.Holder<T> get(int);
  public boolean contains(net.minecraft.core.Holder<T>);
  public boolean canSerializeIn(net.minecraft.core.HolderOwner<T>);
  public java.util.Optional<net.minecraft.tags.TagKey<T>> unwrapKey();
  public java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.core.HolderLookup$RegistryLookup<T> registryLookup();
}
