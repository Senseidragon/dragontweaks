Compiled from "IBuildingContainer.java"
public interface com.minecolonies.api.colony.buildings.IBuildingContainer extends com.minecolonies.api.colony.buildings.ISchematicProvider,com.minecolonies.api.util.IItemHandlerCapProvider {
  public abstract void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract net.minecraft.nbt.CompoundTag serializeNBT(net.minecraft.core.HolderLookup$Provider);
  public abstract int getPickUpPriority();
  public abstract void alterPickUpPriority(int);
  public abstract void addContainerPosition(net.minecraft.core.BlockPos);
  public abstract void removeContainerPosition(net.minecraft.core.BlockPos);
  public abstract java.util.List<net.minecraft.core.BlockPos> getContainers();
  public abstract void registerBlockPosition(net.minecraft.world.level.block.state.BlockState, net.minecraft.core.BlockPos, net.minecraft.world.level.Level);
  public abstract void registerBlockPosition(net.minecraft.world.level.block.Block, net.minecraft.core.BlockPos, net.minecraft.world.level.Level);
  public abstract com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding getTileEntity();
  public abstract void setTileEntity(com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding);
  public default void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.Tag);
  public default net.minecraft.nbt.Tag serializeNBT(net.minecraft.core.HolderLookup$Provider);
}
