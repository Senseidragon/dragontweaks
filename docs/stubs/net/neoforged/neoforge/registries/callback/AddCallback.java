Compiled from "AddCallback.java"
public interface net.neoforged.neoforge.registries.callback.AddCallback<T> extends net.neoforged.neoforge.registries.callback.RegistryCallback<T> {
  public abstract void onAdd(net.minecraft.core.Registry<T>, int, net.minecraft.resources.ResourceKey<T>, T);
}
