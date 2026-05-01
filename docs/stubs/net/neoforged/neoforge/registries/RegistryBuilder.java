Compiled from "RegistryBuilder.java"
public class net.neoforged.neoforge.registries.RegistryBuilder<T> {
  public net.neoforged.neoforge.registries.RegistryBuilder(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>>);
  public net.neoforged.neoforge.registries.RegistryBuilder<T> defaultKey(net.minecraft.resources.ResourceLocation);
  public net.neoforged.neoforge.registries.RegistryBuilder<T> defaultKey(net.minecraft.resources.ResourceKey<T>);
  public net.neoforged.neoforge.registries.RegistryBuilder<T> withIntrusiveHolders();
  public net.neoforged.neoforge.registries.RegistryBuilder<T> callback(net.neoforged.neoforge.registries.callback.RegistryCallback<T>);
  public net.neoforged.neoforge.registries.RegistryBuilder<T> onAdd(net.neoforged.neoforge.registries.callback.AddCallback<T>);
  public net.neoforged.neoforge.registries.RegistryBuilder<T> onBake(net.neoforged.neoforge.registries.callback.BakeCallback<T>);
  public net.neoforged.neoforge.registries.RegistryBuilder<T> onClear(net.neoforged.neoforge.registries.callback.ClearCallback<T>);
  public net.neoforged.neoforge.registries.RegistryBuilder<T> maxId(int);
  public net.neoforged.neoforge.registries.RegistryBuilder<T> sync(boolean);
  public net.neoforged.neoforge.registries.RegistryBuilder<T> disableRegistrationCheck();
  public net.minecraft.core.Registry<T> create();
}
