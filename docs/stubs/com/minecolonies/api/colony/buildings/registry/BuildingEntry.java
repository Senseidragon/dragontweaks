Compiled from "BuildingEntry.java"
public class com.minecolonies.api.colony.buildings.registry.BuildingEntry {
  public java.lang.String getTranslationKey();
  public com.minecolonies.api.blocks.AbstractColonyBlock<?> getBuildingBlock();
  public com.minecolonies.api.colony.buildings.IBuilding produceBuilding(net.minecraft.core.BlockPos, com.minecolonies.api.colony.IColony);
  public com.minecolonies.api.colony.buildings.views.IBuildingView produceBuildingView(net.minecraft.core.BlockPos, com.minecolonies.api.colony.IColonyView);
  public java.util.List<com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer> getModuleProducers();
  public net.minecraft.resources.ResourceLocation getRegistryName();
  public static com.minecolonies.api.colony.buildings.modules.IBuildingModule produceModuleWithoutBuilding(java.lang.String);
  public static com.minecolonies.api.colony.buildings.modules.IBuildingModuleView produceViewWithoutBuilding(java.lang.String, com.minecolonies.api.colony.IColonyView);
  public static java.util.Map<java.lang.String, com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer> getALlModuleProducers();
  public static com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer getProducer(java.lang.String);
  public static com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer getProducer(int);
}
