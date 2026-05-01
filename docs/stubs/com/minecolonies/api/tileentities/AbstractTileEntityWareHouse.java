Compiled from "AbstractTileEntityWareHouse.java"
public abstract class com.minecolonies.api.tileentities.AbstractTileEntityWareHouse extends com.minecolonies.core.tileentities.TileEntityColonyBuilding {
  public com.minecolonies.api.tileentities.AbstractTileEntityWareHouse(net.minecraft.world.level.block.entity.BlockEntityType<? extends com.minecolonies.api.tileentities.AbstractTileEntityWareHouse>, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public abstract boolean hasMatchingItemStackInWarehouse(java.util.function.Predicate<net.minecraft.world.item.ItemStack>, int);
  public abstract boolean hasMatchingItemStackInWarehouse(net.minecraft.world.item.ItemStack, int, boolean);
  public abstract boolean hasMatchingItemStackInWarehouse(net.minecraft.world.item.ItemStack, int, boolean, int);
  public abstract boolean hasMatchingItemStackInWarehouse(net.minecraft.world.item.ItemStack, int, boolean, boolean, int);
  public abstract java.util.List<com.minecolonies.api.util.Tuple<net.minecraft.world.item.ItemStack, net.minecraft.core.BlockPos>> getMatchingItemStacksInWarehouse(java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public abstract void dumpInventoryIntoWareHouse(com.minecolonies.api.inventory.InventoryCitizen);
}
