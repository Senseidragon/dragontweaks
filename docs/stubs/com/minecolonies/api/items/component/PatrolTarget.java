Compiled from "PatrolTarget.java"
public final class com.minecolonies.api.items.component.PatrolTarget extends java.lang.Record {
  public static final com.minecolonies.api.items.component.PatrolTarget EMPTY;
  public static final com.mojang.serialization.Codec<com.minecolonies.api.items.component.PatrolTarget> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.items.component.PatrolTarget> STREAM_CODEC;
  public com.minecolonies.api.items.component.PatrolTarget(net.minecraft.core.BlockPos);
  public boolean hasPos();
  public void writeToItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.items.component.PatrolTarget readFromItemStack(net.minecraft.world.item.ItemStack);
  public static void updateItemStack(net.minecraft.world.item.ItemStack, java.util.function.UnaryOperator<com.minecolonies.api.items.component.PatrolTarget>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.core.BlockPos pos();
}
