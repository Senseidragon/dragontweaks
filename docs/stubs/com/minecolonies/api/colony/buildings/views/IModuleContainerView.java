Compiled from "IModuleContainerView.java"
public interface com.minecolonies.api.colony.buildings.views.IModuleContainerView {
  public abstract <T extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView> T getModuleViewByType(java.lang.Class<T>);
  public abstract com.minecolonies.api.colony.buildings.modules.IBuildingModuleView getModuleView(int);
  public abstract <M extends com.minecolonies.api.colony.buildings.modules.IBuildingModule, V extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView> V getModuleView(com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer<M, V>);
  public abstract boolean hasModuleView(com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer);
  public abstract <T extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView> T getModuleViewMatching(java.lang.Class<T>, java.util.function.Predicate<? super T>);
  public abstract <T extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView> java.util.List<T> getModuleViews(java.lang.Class<T>);
  public abstract void registerModule(com.minecolonies.api.colony.buildings.modules.IBuildingModuleView);
  public abstract java.util.List<com.minecolonies.api.colony.buildings.modules.IBuildingModuleView> getAllModuleViews();
}
