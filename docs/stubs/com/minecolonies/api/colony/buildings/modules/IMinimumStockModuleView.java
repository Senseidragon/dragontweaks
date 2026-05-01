Compiled from "IMinimumStockModuleView.java"
public interface com.minecolonies.api.colony.buildings.modules.IMinimumStockModuleView extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView {
  public abstract java.util.List<com.minecolonies.api.util.Tuple<com.minecolonies.api.crafting.ItemStorage, java.lang.Integer>> getStock();
  public abstract boolean hasReachedLimit();
}
