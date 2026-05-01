Compiled from "AbstractBuildingModuleView.java"
public abstract class com.minecolonies.api.colony.buildings.modules.AbstractBuildingModuleView implements com.minecolonies.api.colony.buildings.modules.IBuildingModuleView {
  public com.minecolonies.api.colony.buildings.modules.AbstractBuildingModuleView();
  public com.minecolonies.api.colony.buildings.modules.IBuildingModuleView setBuildingView(com.minecolonies.api.colony.buildings.views.IBuildingView);
  public com.minecolonies.api.colony.buildings.modules.IBuildingModuleView setColonyView(com.minecolonies.api.colony.IColonyView);
  public com.minecolonies.api.colony.IColonyView getColony();
  public boolean canBeHiredAs(com.minecolonies.api.colony.jobs.registry.JobEntry);
  public com.minecolonies.api.colony.buildings.views.IBuildingView getBuildingView();
  public <M extends com.minecolonies.api.colony.buildings.modules.IBuildingModule, V extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView> com.minecolonies.api.colony.buildings.modules.IBuildingModuleView setProducer(com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer<M, V>);
  public <M extends com.minecolonies.api.colony.buildings.modules.IBuildingModule, V extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView> com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer<M, V> getProducer();
}
