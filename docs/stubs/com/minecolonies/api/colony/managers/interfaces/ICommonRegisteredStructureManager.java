Compiled from "ICommonRegisteredStructureManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.ICommonRegisteredStructureManager<B extends com.minecolonies.api.colony.buildings.ICommonBuilding, T> {
  public abstract T getTownHall();
  public abstract boolean hasWarehouse();
  public default B getBuilding(net.minecraft.core.BlockPos);
  public abstract java.util.Map<net.minecraft.core.BlockPos, B> getBuildings();
  public abstract boolean hasTownHall();
  public default <BB extends B> BB getBuilding(net.minecraft.core.BlockPos, java.lang.Class<BB>);
  public default net.minecraft.core.BlockPos getBestBuilding(com.minecolonies.api.entity.citizen.AbstractEntityCitizen, java.lang.Class<? extends B>);
  public default <BB extends B> net.minecraft.core.BlockPos getBestBuilding(com.minecolonies.api.entity.citizen.AbstractEntityCitizen, java.lang.Class<BB>, java.util.function.Predicate<BB>);
  public default net.minecraft.core.BlockPos getBestBuilding(net.minecraft.core.BlockPos, java.lang.Class<? extends B>);
  public default <BB extends B> net.minecraft.core.BlockPos getBestBuilding(net.minecraft.core.BlockPos, java.lang.Class<BB>, java.util.function.Predicate<BB>);
  public default net.minecraft.core.BlockPos getRandomBuilding(java.util.function.Predicate<B>);
  public default B getFirstBuildingMatching(java.util.function.Predicate<B>);
  public default boolean hasBuilding(net.minecraft.resources.ResourceLocation, int, boolean);
  public abstract java.util.List<com.minecolonies.api.colony.buildingextensions.IBuildingExtension> getBuildingExtensions(java.util.function.Predicate<com.minecolonies.api.colony.buildingextensions.IBuildingExtension>);
  public abstract com.minecolonies.api.colony.IColony getColony();
}
