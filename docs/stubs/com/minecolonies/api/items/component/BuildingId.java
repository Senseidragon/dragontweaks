Compiled from "BuildingId.java"
public final class com.minecolonies.api.items.component.BuildingId extends java.lang.Record {
  public static final com.minecolonies.api.items.component.BuildingId EMPTY;
  public static final com.mojang.serialization.Codec<com.minecolonies.api.items.component.BuildingId> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.items.component.BuildingId> STREAM_CODEC;
  public com.minecolonies.api.items.component.BuildingId(net.minecraft.core.BlockPos);
  public boolean hasId();
  public void writeToItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.items.component.BuildingId readFromItemStack(net.minecraft.world.item.ItemStack);
  public static void updateItemStack(net.minecraft.world.item.ItemStack, java.util.function.UnaryOperator<com.minecolonies.api.items.component.BuildingId>);
  public static com.minecolonies.api.colony.buildings.IBuilding readBuildingFromItemStack(net.minecraft.world.item.ItemStack);
  public static com.minecolonies.api.colony.buildings.views.IBuildingView readBuildingViewFromItemStack(net.minecraft.world.item.ItemStack);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.core.BlockPos id();
}
