Compiled from "IBuildingModuleContainer.java"
public interface com.minecolonies.api.colony.modules.IBuildingModuleContainer extends com.minecolonies.api.colony.modules.IModuleContainer<com.minecolonies.api.colony.buildings.modules.IBuildingModule> {
  public abstract boolean hasModule(com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer<?, ?>);
  public abstract <M extends com.minecolonies.api.colony.buildings.modules.IBuildingModule, V extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView> M getModule(com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer<M, V>);
  public abstract com.minecolonies.api.colony.buildings.modules.IBuildingModule getModule(int);
  public abstract void registerModule(com.minecolonies.api.colony.buildings.modules.IBuildingModule);
}
