Compiled from "AdventureData.java"
public final class com.minecolonies.api.items.component.AdventureData extends java.lang.Record {
  public static final com.mojang.serialization.Codec<com.minecolonies.api.items.component.AdventureData> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.items.component.AdventureData> STREAM_CODEC;
  public com.minecolonies.api.items.component.AdventureData(net.minecraft.world.entity.EntityType<?>, float, int);
  public void writeToItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.items.component.AdventureData readFromItemStack(net.minecraft.world.item.ItemStack);
  public static void updateItemStack(net.minecraft.world.item.ItemStack, java.util.function.UnaryOperator<com.minecolonies.api.items.component.AdventureData>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.world.entity.EntityType<?> entityType();
  public float damage();
  public int xp();
}
