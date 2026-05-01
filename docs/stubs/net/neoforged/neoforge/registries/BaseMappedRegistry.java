Compiled from "BaseMappedRegistry.java"
public abstract class net.neoforged.neoforge.registries.BaseMappedRegistry<T> implements net.minecraft.core.Registry<T> {
  public net.neoforged.neoforge.registries.BaseMappedRegistry();
  public boolean doesSync();
  public int getMaxId();
  public void addCallback(net.neoforged.neoforge.registries.callback.RegistryCallback<T>);
  public void addAlias(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation);
  public net.minecraft.resources.ResourceLocation resolve(net.minecraft.resources.ResourceLocation);
  public net.minecraft.resources.ResourceKey<T> resolve(net.minecraft.resources.ResourceKey<T>);
  public int getId(net.minecraft.resources.ResourceKey<T>);
  public int getId(net.minecraft.resources.ResourceLocation);
  public <A> A getData(net.neoforged.neoforge.registries.datamaps.DataMapType<T, A>, net.minecraft.resources.ResourceKey<T>);
  public <A> java.util.Map<net.minecraft.resources.ResourceKey<T>, A> getDataMap(net.neoforged.neoforge.registries.datamaps.DataMapType<T, A>);
}
