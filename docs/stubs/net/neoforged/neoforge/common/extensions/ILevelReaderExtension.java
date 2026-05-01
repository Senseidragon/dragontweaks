Compiled from "ILevelReaderExtension.java"
public interface net.neoforged.neoforge.common.extensions.ILevelReaderExtension {
  public default boolean isAreaLoaded(net.minecraft.core.BlockPos, int);
  public default <T> net.minecraft.core.Holder<T> holderOrThrow(net.minecraft.resources.ResourceKey<T>);
  public default <T> java.util.Optional<net.minecraft.core.Holder$Reference<T>> holder(net.minecraft.resources.ResourceKey<T>);
}
