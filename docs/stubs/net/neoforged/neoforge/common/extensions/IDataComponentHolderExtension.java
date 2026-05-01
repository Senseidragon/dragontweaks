Compiled from "IDataComponentHolderExtension.java"
public interface net.neoforged.neoforge.common.extensions.IDataComponentHolderExtension {
  public default <T> T get(java.util.function.Supplier<? extends net.minecraft.core.component.DataComponentType<? extends T>>);
  public default <T> T getOrDefault(java.util.function.Supplier<? extends net.minecraft.core.component.DataComponentType<? extends T>>, T);
  public default boolean has(java.util.function.Supplier<? extends net.minecraft.core.component.DataComponentType<?>>);
  public default <T extends net.minecraft.world.item.component.TooltipProvider> void addToTooltip(net.minecraft.core.component.DataComponentType<T>, net.minecraft.world.item.Item$TooltipContext, java.util.function.Consumer<net.minecraft.network.chat.Component>, net.minecraft.world.item.TooltipFlag);
  public default <T extends net.minecraft.world.item.component.TooltipProvider> void addToTooltip(java.util.function.Supplier<? extends net.minecraft.core.component.DataComponentType<T>>, net.minecraft.world.item.Item$TooltipContext, java.util.function.Consumer<net.minecraft.network.chat.Component>, net.minecraft.world.item.TooltipFlag);
}
