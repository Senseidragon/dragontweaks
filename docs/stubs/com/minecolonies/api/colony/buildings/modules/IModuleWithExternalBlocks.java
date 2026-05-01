Compiled from "IModuleWithExternalBlocks.java"
public interface com.minecolonies.api.colony.buildings.modules.IModuleWithExternalBlocks extends com.minecolonies.api.colony.buildings.modules.IBuildingModule {
  public abstract void onBlockPlacedInBuilding(net.minecraft.world.level.block.state.BlockState, net.minecraft.core.BlockPos, net.minecraft.world.level.Level);
  public abstract java.util.List<net.minecraft.core.BlockPos> getRegisteredBlocks();
}
