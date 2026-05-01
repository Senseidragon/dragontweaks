Compiled from "IPlantationModule.java"
public interface com.minecolonies.api.colony.buildingextensions.plantation.IPlantationModule extends com.minecolonies.api.colony.buildingextensions.modules.IBuildingExtensionModule {
  public abstract java.lang.String getFieldTag();
  public abstract java.lang.String getWorkTag();
  public abstract net.minecraft.world.item.Item getItem();
  public abstract int getPlantsToRequest();
  public abstract net.minecraft.resources.ResourceLocation getRequiredResearchEffect();
  public abstract com.minecolonies.api.colony.buildingextensions.plantation.IPlantationModule$PlantationModuleResult$Builder decideFieldWork(net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public abstract net.minecraft.core.BlockPos getNextWorkingPosition(net.minecraft.world.level.Level);
  public abstract java.util.List<net.minecraft.core.BlockPos> getValidWorkingPositions(net.minecraft.world.level.Level, java.util.List<net.minecraft.core.BlockPos>);
  public abstract int getActionLimit();
  public abstract java.util.List<net.minecraft.world.item.Item> getValidBonemeal();
  public abstract net.minecraft.core.BlockPos getPositionToWalkTo(net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public abstract net.minecraft.world.level.block.state.BlockState getPlantingBlockState(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public abstract void applyBonemeal(com.minecolonies.api.entity.citizen.AbstractEntityCitizen, net.minecraft.core.BlockPos, net.minecraft.world.item.ItemStack, net.minecraft.world.entity.player.Player);
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getRequiredItemsForOperation();
  public abstract com.minecolonies.api.equipment.registry.EquipmentTypeEntry getRequiredTool();
  public abstract int hashCode();
  public abstract boolean equals(java.lang.Object);
}
