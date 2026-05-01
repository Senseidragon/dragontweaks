Compiled from "HutBlockData.java"
public final class com.minecolonies.api.items.component.HutBlockData extends java.lang.Record implements net.minecraft.world.item.component.TooltipProvider {
  public static final com.mojang.serialization.Codec<com.minecolonies.api.items.component.HutBlockData> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.items.component.HutBlockData> STREAM_CODEC;
  public com.minecolonies.api.items.component.HutBlockData(int, boolean);
  public void writeToItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.items.component.HutBlockData readFromItemStack(net.minecraft.world.item.ItemStack);
  public static void updateItemStack(net.minecraft.world.item.ItemStack, java.util.function.UnaryOperator<com.minecolonies.api.items.component.HutBlockData>);
  public void addToTooltip(net.minecraft.world.item.Item$TooltipContext, java.util.function.Consumer<net.minecraft.network.chat.Component>, net.minecraft.world.item.TooltipFlag);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public int level();
  public boolean pastable();
}
