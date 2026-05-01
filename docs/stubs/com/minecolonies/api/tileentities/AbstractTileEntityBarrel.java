Compiled from "AbstractTileEntityBarrel.java"
public abstract class com.minecolonies.api.tileentities.AbstractTileEntityBarrel extends net.minecraft.world.level.block.entity.BlockEntity {
  public static final int MAX_ITEMS;
  public com.minecolonies.api.tileentities.AbstractTileEntityBarrel(net.minecraft.world.level.block.entity.BlockEntityType<?>, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public abstract int getItems();
  public abstract boolean isDone();
  public abstract boolean checkIfWorking();
  public abstract boolean addItem(net.minecraft.world.item.ItemStack);
  public abstract net.minecraft.world.item.ItemStack retrieveCompost(double);
}
