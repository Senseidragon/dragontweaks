Compiled from "AbstractBuildingModule.java"
public abstract class com.minecolonies.api.colony.buildings.modules.AbstractBuildingModule implements com.minecolonies.api.colony.buildings.modules.IBuildingModule {
  public boolean isDirty;
  public com.minecolonies.api.colony.buildings.modules.AbstractBuildingModule();
  public void markDirty();
  public void clearDirty();
  public boolean checkDirty();
  public com.minecolonies.api.colony.buildings.IBuilding getBuilding();
  public com.minecolonies.api.colony.buildings.modules.IBuildingModule setBuilding(com.minecolonies.api.colony.buildings.IBuilding);
  public com.minecolonies.api.colony.buildings.modules.IBuildingModule setProducer(com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer);
  public com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer getProducer();
}
