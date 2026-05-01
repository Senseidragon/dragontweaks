Compiled from "Desc.java"
public final class com.minecolonies.api.items.component.Desc extends java.lang.Record {
  public static final com.minecolonies.api.items.component.Desc EMPTY;
  public static final com.mojang.serialization.Codec<com.minecolonies.api.items.component.Desc> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.items.component.Desc> STREAM_CODEC;
  public com.minecolonies.api.items.component.Desc(net.minecraft.network.chat.Component);
  public com.minecolonies.api.items.component.Desc(net.minecraft.network.chat.MutableComponent);
  public boolean isEmpty();
  public void writeToItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.items.component.Desc readFromItemStack(net.minecraft.world.item.ItemStack);
  public static void updateItemStack(net.minecraft.world.item.ItemStack, java.util.function.UnaryOperator<com.minecolonies.api.items.component.Desc>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.network.chat.MutableComponent desc();
}
