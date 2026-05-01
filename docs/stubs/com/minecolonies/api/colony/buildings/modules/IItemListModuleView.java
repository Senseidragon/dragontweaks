Compiled from "IItemListModuleView.java"
public interface com.minecolonies.api.colony.buildings.modules.IItemListModuleView extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView {
  public abstract void addItem(com.minecolonies.api.crafting.ItemStorage);
  public abstract boolean isAllowedItem(com.minecolonies.api.crafting.ItemStorage);
  public abstract int getSize();
  public abstract void removeItem(com.minecolonies.api.crafting.ItemStorage);
  public abstract java.lang.String getId();
  public abstract java.util.function.Function<com.minecolonies.api.colony.buildings.views.IBuildingView, java.util.Set<com.minecolonies.api.crafting.ItemStorage>> getAllItems();
  public abstract boolean isInverted();
  public abstract void clearItems();
}
