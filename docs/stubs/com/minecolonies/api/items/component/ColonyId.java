Compiled from "ColonyId.java"
public final class com.minecolonies.api.items.component.ColonyId extends java.lang.Record implements net.minecraft.world.item.component.TooltipProvider {
  public static final com.minecolonies.api.items.component.ColonyId EMPTY;
  public static final com.mojang.serialization.Codec<com.minecolonies.api.items.component.ColonyId> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.items.component.ColonyId> STREAM_CODEC;
  public com.minecolonies.api.items.component.ColonyId(int, net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level>);
  public boolean hasColonyId();
  public void writeToItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.items.component.ColonyId readFromItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.colony.IColony readColonyFromItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.colony.IColonyView readColonyViewFromItemStack(net.minecraft.world.item.ItemStack);
  public static void updateItemStack(net.minecraft.world.item.ItemStack, java.util.function.UnaryOperator<com.minecolonies.api.items.component.ColonyId>);
  public void addToTooltip(net.minecraft.world.item.Item$TooltipContext, java.util.function.Consumer<net.minecraft.network.chat.Component>, net.minecraft.world.item.TooltipFlag);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public int id();
  public net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level> dimension();
}
