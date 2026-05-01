Compiled from "IItemListModule.java"
public interface com.minecolonies.api.colony.buildings.modules.IItemListModule {
  public abstract void addItem(com.minecolonies.api.crafting.ItemStorage);
  public abstract boolean isItemInList(com.minecolonies.api.crafting.ItemStorage);
  public abstract void removeItem(com.minecolonies.api.crafting.ItemStorage);
  public abstract java.util.List<com.minecolonies.api.crafting.ItemStorage> getList();
  public abstract java.lang.String getListIdentifier();
  public abstract void clearItems();
  public abstract void resetToDefaults();
  public abstract java.lang.String getId();
}
