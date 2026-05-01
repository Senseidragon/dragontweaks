Compiled from "NotHolderSet.java"
public class net.neoforged.neoforge.registries.holdersets.NotHolderSet<T> implements net.neoforged.neoforge.registries.holdersets.ICustomHolderSet<T> {
  public net.minecraft.core.HolderLookup$RegistryLookup<T> registryLookup();
  public net.minecraft.core.HolderSet<T> value();
  public net.neoforged.neoforge.registries.holdersets.NotHolderSet(net.minecraft.core.HolderLookup$RegistryLookup<T>, net.minecraft.core.HolderSet<T>);
  public net.neoforged.neoforge.registries.holdersets.HolderSetType type();
  public void addInvalidationListener(java.lang.Runnable);
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
}
