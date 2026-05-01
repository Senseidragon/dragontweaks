Compiled from "SupplyData.java"
public final class com.minecolonies.api.items.component.SupplyData extends java.lang.Record {
  public static final com.minecolonies.api.items.component.SupplyData EMPTY;
  public static final com.mojang.serialization.Codec<com.minecolonies.api.items.component.SupplyData> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.items.component.SupplyData> STREAM_CODEC;
  public com.minecolonies.api.items.component.SupplyData(boolean, boolean, long);
  public com.minecolonies.api.items.component.SupplyData withSawStory(boolean);
  public com.minecolonies.api.items.component.SupplyData withRandomKey(long);
  public boolean hasRandomKey();
  public void writeToItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.items.component.SupplyData readFromItemStack(net.minecraft.world.item.ItemStack);
  public static void updateItemStack(net.minecraft.world.item.ItemStack, java.util.function.UnaryOperator<com.minecolonies.api.items.component.SupplyData>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public boolean sawStory();
  public boolean instantPlacement();
  public long randomKey();
}
