Compiled from "IWareHouse.java"
public interface com.minecolonies.api.colony.buildings.workerbuildings.IWareHouse extends com.minecolonies.api.colony.buildings.IBuilding {
  public abstract boolean canAccessWareHouse(com.minecolonies.api.colony.ICitizenData);
  public abstract void upgradeContainers(net.minecraft.world.level.Level);
  public abstract com.minecolonies.api.tileentities.AbstractTileEntityWareHouse getTileEntity();
  public abstract boolean hasContainerPosition(net.minecraft.core.BlockPos);
  public default com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding getTileEntity();
}
