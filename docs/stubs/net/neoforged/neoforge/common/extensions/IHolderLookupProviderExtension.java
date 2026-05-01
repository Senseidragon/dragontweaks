Compiled from "IHolderLookupProviderExtension.java"
public interface net.neoforged.neoforge.common.extensions.IHolderLookupProviderExtension {
  public default <T> net.minecraft.core.Holder<T> holderOrThrow(net.minecraft.resources.ResourceKey<T>);
  public default <T> java.util.Optional<net.minecraft.core.Holder$Reference<T>> holder(net.minecraft.resources.ResourceKey<T>);
}
