Compiled from "IBuildingDataManager.java"
public interface com.minecolonies.api.colony.buildings.registry.IBuildingDataManager {
  public static com.minecolonies.api.colony.buildings.registry.IBuildingDataManager getInstance();
  public abstract com.minecolonies.api.colony.buildings.IBuilding createFrom(com.minecolonies.api.colony.IColony, net.minecraft.nbt.CompoundTag, net.minecraft.core.HolderLookup$Provider);
  public abstract com.minecolonies.api.colony.buildings.IBuilding createFrom(com.minecolonies.api.colony.IColony, com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding);
  public abstract com.minecolonies.api.colony.buildings.IBuilding createFrom(com.minecolonies.api.colony.IColony, net.minecraft.core.BlockPos, net.minecraft.resources.ResourceLocation);
  public abstract com.minecolonies.api.colony.buildings.views.IBuildingView createViewFrom(com.minecolonies.api.colony.IColonyView, net.minecraft.core.BlockPos, net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract void openBuildingBrowser(net.minecraft.world.level.block.Block);
}
