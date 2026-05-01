Compiled from "AbstractTileEntityRack.java"
public abstract class com.minecolonies.api.tileentities.AbstractTileEntityRack extends net.minecraft.world.level.block.entity.BlockEntity implements net.minecraft.world.MenuProvider,com.minecolonies.api.util.IItemHandlerCapProvider {
  public com.minecolonies.api.tileentities.AbstractTileEntityRack(net.minecraft.world.level.block.entity.BlockEntityType<?>, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public com.minecolonies.api.tileentities.AbstractTileEntityRack(net.minecraft.world.level.block.entity.BlockEntityType<?>, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, int);
  public abstract net.neoforged.neoforge.items.ItemStackHandler createInventory(int);
  public void updateWarehouseIfAvailable(net.minecraft.world.item.ItemStack);
  public abstract void setInWarehouse(java.lang.Boolean);
  public abstract int getFreeSlots();
  public abstract boolean hasItemStack(net.minecraft.world.item.ItemStack, int, boolean);
  public abstract boolean hasItemStorage(com.minecolonies.api.crafting.ItemStorage, int);
  public abstract int getCount(net.minecraft.world.item.ItemStack, boolean, boolean);
  public abstract int getCount(com.minecolonies.api.crafting.ItemStorage);
  public abstract boolean hasItemStack(java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public abstract boolean hasSimilarStack(net.minecraft.world.item.ItemStack);
  public abstract void upgradeRackSize();
  public void setBuildingPos(net.minecraft.core.BlockPos);
  public abstract int getUpgradeSize();
  public abstract int getItemCount(java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public abstract void updateItemStorage();
  public abstract com.minecolonies.api.tileentities.AbstractTileEntityRack getOtherChest();
  public abstract boolean isEmpty();
  public net.neoforged.neoforge.items.IItemHandlerModifiable getInventory();
}
