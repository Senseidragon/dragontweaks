Compiled from "IRegisteredStructureManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.IRegisteredStructureManager extends com.minecolonies.api.colony.managers.interfaces.ICommonRegisteredStructureManager<com.minecolonies.api.colony.buildings.IBuilding, com.minecolonies.api.colony.buildings.workerbuildings.ITownHall> {
  public abstract void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void clearDirty();
  public abstract void sendPackets(java.util.Set<net.minecraft.server.level.ServerPlayer>, java.util.Set<net.minecraft.server.level.ServerPlayer>);
  public abstract void onColonyTick(com.minecolonies.api.colony.IColony);
  public abstract void cleanUpBuildings(com.minecolonies.api.colony.IColony);
  public abstract java.util.List<net.minecraft.core.BlockPos> getLeisureSites();
  public abstract void addLeisureSite(net.minecraft.core.BlockPos);
  public abstract void removeLeisureSite(net.minecraft.core.BlockPos);
  public abstract com.minecolonies.api.colony.buildings.workerbuildings.IWareHouse getClosestWarehouseInColony(net.minecraft.core.BlockPos);
  public abstract int getMysticalSiteMaxBuildingLevel();
  public abstract boolean hasMysticalSite();
  public abstract void removeBuilding(com.minecolonies.api.colony.buildings.IBuilding, java.util.Set<net.minecraft.server.level.ServerPlayer>);
  public abstract void markBuildingsDirty();
  public abstract void markBuildingExtensionsDirty();
  public abstract com.minecolonies.api.colony.buildings.IBuilding addNewBuilding(com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding, net.minecraft.world.level.Level);
  public abstract boolean hasGuardBuildingNear(com.minecolonies.api.colony.buildings.IBuilding);
  public abstract void guardBuildingChangedAt(com.minecolonies.api.colony.buildings.IBuilding, int);
  public abstract void setTownHall(com.minecolonies.api.colony.buildings.workerbuildings.ITownHall);
  public abstract void removeWareHouse(com.minecolonies.api.colony.buildings.workerbuildings.IWareHouse);
  public abstract java.util.List<com.minecolonies.api.colony.buildings.workerbuildings.IWareHouse> getWareHouses();
  public abstract void removeMysticalSite(com.minecolonies.api.colony.buildings.IMysticalSite);
  public abstract java.util.List<com.minecolonies.api.colony.buildings.IMysticalSite> getMysticalSites();
  public abstract boolean canPlaceAt(net.minecraft.world.level.block.Block, net.minecraft.core.BlockPos, net.minecraft.world.entity.player.Player);
  public abstract boolean keepChunkColonyLoaded(net.minecraft.world.level.chunk.LevelChunk);
  public abstract com.minecolonies.api.colony.buildings.IBuilding getHouseWithSpareBed();
  public abstract void onBuildingUpgradeComplete(com.minecolonies.api.colony.buildings.IBuilding, int);
  public abstract net.minecraft.core.BlockPos getRandomLeisureSite();
  public abstract java.util.Optional<com.minecolonies.api.colony.buildingextensions.IBuildingExtension> getMatchingBuildingExtension(java.util.function.Predicate<com.minecolonies.api.colony.buildingextensions.IBuildingExtension>);
  public abstract boolean addBuildingExtension(com.minecolonies.api.colony.buildingextensions.IBuildingExtension);
  public abstract void removeBuildingExtension(java.util.function.Predicate<com.minecolonies.api.colony.buildingextensions.IBuildingExtension>);
  public abstract com.minecolonies.api.colony.buildingextensions.IBuildingExtension getMatchingBuildingExtension(com.minecolonies.api.colony.buildingextensions.IBuildingExtension$ExtensionId);
  public abstract void addBuildingExtensionIfMissing(com.minecolonies.api.colony.buildingextensions.registry.BuildingExtensionRegistries$BuildingExtensionEntry, net.minecraft.core.BlockPos, net.minecraft.world.entity.player.Player);
  public abstract void clearPendingPrestigeCalc(com.minecolonies.api.colony.buildings.IBuilding);
  public abstract int getColonyPrestige();
}
