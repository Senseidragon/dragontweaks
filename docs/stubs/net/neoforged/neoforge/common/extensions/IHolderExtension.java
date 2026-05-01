Compiled from "IHolderExtension.java"
public interface net.neoforged.neoforge.common.extensions.IHolderExtension<T> extends net.neoforged.neoforge.registries.datamaps.IWithData<T> {
  public default net.minecraft.core.Holder<T> getDelegate();
  public default net.minecraft.core.HolderLookup$RegistryLookup<T> unwrapLookup();
  public default net.minecraft.resources.ResourceKey<T> getKey();
}
