Compiled from "IRegistryExtension.java"
public interface net.neoforged.neoforge.registries.IRegistryExtension<T> {
  public abstract boolean doesSync();
  public abstract int getMaxId();
  public abstract void addCallback(net.neoforged.neoforge.registries.callback.RegistryCallback<T>);
  public default <C extends net.neoforged.neoforge.registries.callback.RegistryCallback<T>> void addCallback(java.lang.Class<C>, C);
  public abstract void addAlias(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation);
  public abstract net.minecraft.resources.ResourceLocation resolve(net.minecraft.resources.ResourceLocation);
  public abstract net.minecraft.resources.ResourceKey<T> resolve(net.minecraft.resources.ResourceKey<T>);
  public abstract int getId(net.minecraft.resources.ResourceKey<T>);
  public abstract int getId(net.minecraft.resources.ResourceLocation);
  public abstract boolean containsValue(T);
  public abstract <A> A getData(net.neoforged.neoforge.registries.datamaps.DataMapType<T, A>, net.minecraft.resources.ResourceKey<T>);
  public abstract <A> java.util.Map<net.minecraft.resources.ResourceKey<T>, A> getDataMap(net.neoforged.neoforge.registries.datamaps.DataMapType<T, A>);
  public default net.minecraft.resources.ResourceLocation getKeyOrNull(T);
}
