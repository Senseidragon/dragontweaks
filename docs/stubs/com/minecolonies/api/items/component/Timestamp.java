Compiled from "Timestamp.java"
public final class com.minecolonies.api.items.component.Timestamp extends java.lang.Record {
  public static final com.minecolonies.api.items.component.Timestamp EMPTY;
  public static final com.mojang.serialization.Codec<com.minecolonies.api.items.component.Timestamp> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.items.component.Timestamp> STREAM_CODEC;
  public com.minecolonies.api.items.component.Timestamp(long);
  public void writeToItemStack(net.minecraft.world.item.ItemStack);
  public boolean hasTime();
  public static com.minecolonies.api.items.component.Timestamp readFromItemStack(net.minecraft.world.item.ItemStack);
  public static void updateItemStack(net.minecraft.world.item.ItemStack, java.util.function.UnaryOperator<com.minecolonies.api.items.component.Timestamp>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public long time();
}
