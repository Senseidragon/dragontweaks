Compiled from "RallyData.java"
public final class com.minecolonies.api.items.component.RallyData extends java.lang.Record {
  public static final com.minecolonies.api.items.component.RallyData EMPTY;
  public static final com.mojang.serialization.Codec<com.minecolonies.api.items.component.RallyData> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.items.component.RallyData> STREAM_CODEC;
  public com.minecolonies.api.items.component.RallyData(java.util.List<net.minecraft.core.BlockPos>, boolean);
  public com.minecolonies.api.items.component.RallyData withActive(boolean);
  public com.minecolonies.api.items.component.RallyData withPosRemoval(net.minecraft.core.BlockPos);
  public com.minecolonies.api.items.component.RallyData withPosAddition(net.minecraft.core.BlockPos);
  public void writeToItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.items.component.RallyData readFromItemStack(net.minecraft.world.item.ItemStack);
  public static void updateItemStack(net.minecraft.world.item.ItemStack, java.util.function.UnaryOperator<com.minecolonies.api.items.component.RallyData>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.List<net.minecraft.core.BlockPos> towers();
  public boolean active();
}
