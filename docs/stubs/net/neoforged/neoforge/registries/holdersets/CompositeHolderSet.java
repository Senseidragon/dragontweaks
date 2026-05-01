Compiled from "CompositeHolderSet.java"
public abstract class net.neoforged.neoforge.registries.holdersets.CompositeHolderSet<T> implements net.neoforged.neoforge.registries.holdersets.ICustomHolderSet<T> {
  public net.neoforged.neoforge.registries.holdersets.CompositeHolderSet(java.util.List<net.minecraft.core.HolderSet<T>>);
  public java.util.List<net.minecraft.core.HolderSet<T>> getComponents();
  public java.util.Set<net.minecraft.core.Holder<T>> getSet();
  public java.util.List<net.minecraft.core.Holder<T>> getList();
  public void addInvalidationListener(java.lang.Runnable);
  public java.util.stream.Stream<net.minecraft.core.Holder<T>> stream();
  public int size();
  public com.mojang.datafixers.util.Either<net.minecraft.tags.TagKey<T>, java.util.List<net.minecraft.core.Holder<T>>> unwrap();
  public java.util.Optional<net.minecraft.core.Holder<T>> getRandomElement(net.minecraft.util.RandomSource);
  public net.minecraft.core.Holder<T> get(int);
  public boolean contains(net.minecraft.core.Holder<T>);
  public boolean canSerializeIn(net.minecraft.core.HolderOwner<T>);
  public java.util.Optional<net.minecraft.tags.TagKey<T>> unwrapKey();
  public java.util.Iterator<net.minecraft.core.Holder<T>> iterator();
  public java.util.List<net.minecraft.core.HolderSet<T>> homogenize();
  public boolean isHomogenous();
}
