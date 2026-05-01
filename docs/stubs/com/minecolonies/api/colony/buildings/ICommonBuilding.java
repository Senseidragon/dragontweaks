Compiled from "ICommonBuilding.java"
public interface com.minecolonies.api.colony.buildings.ICommonBuilding {
  public abstract int getBuildingLevel();
  public abstract net.minecraft.core.BlockPos getPosition();
  public abstract com.minecolonies.api.colony.buildings.registry.BuildingEntry getBuildingType();
  public default int getBuildingLevelEquivalent();
  public abstract java.util.List<net.minecraft.core.BlockPos> getContainers();
  public abstract com.minecolonies.api.colony.IColony getColony();
  public abstract int getPrestige();
}
