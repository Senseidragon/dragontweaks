Compiled from "WarehouseSnapshot.java"
public final class com.minecolonies.api.items.component.WarehouseSnapshot extends java.lang.Record {
  public static final com.minecolonies.api.items.component.WarehouseSnapshot EMPTY;
  public static final com.mojang.serialization.Codec<com.minecolonies.api.items.component.WarehouseSnapshot> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.items.component.WarehouseSnapshot> STREAM_CODEC;
  public com.minecolonies.api.items.component.WarehouseSnapshot(java.util.Map<java.lang.String, java.lang.Integer>, java.lang.String);
  public void writeToItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.items.component.WarehouseSnapshot readFromItemStack(net.minecraft.world.item.ItemStack);
  public static void updateItemStack(net.minecraft.world.item.ItemStack, java.util.function.UnaryOperator<com.minecolonies.api.items.component.WarehouseSnapshot>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.Map<java.lang.String, java.lang.Integer> snapshot();
  public java.lang.String hash();
}
