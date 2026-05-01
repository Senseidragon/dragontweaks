Compiled from "PermissionMode.java"
public final class com.minecolonies.api.items.component.PermissionMode extends java.lang.Enum<com.minecolonies.api.items.component.PermissionMode> {
  public static final com.minecolonies.api.items.component.PermissionMode LOCATION;
  public static final com.minecolonies.api.items.component.PermissionMode BLOCK;
  public static final com.minecolonies.api.items.component.PermissionMode EMPTY;
  public static final com.mojang.serialization.Codec<com.minecolonies.api.items.component.PermissionMode> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<io.netty.buffer.ByteBuf, com.minecolonies.api.items.component.PermissionMode> STREAM_CODEC;
  public static com.minecolonies.api.items.component.PermissionMode[] values();
  public static com.minecolonies.api.items.component.PermissionMode valueOf(java.lang.String);
  public void writeToItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.items.component.PermissionMode readFromItemStack(net.minecraft.world.item.ItemStack);
  public static void updateItemStack(net.minecraft.world.item.ItemStack, java.util.function.UnaryOperator<com.minecolonies.api.items.component.PermissionMode>);
}
