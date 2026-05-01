Compiled from "ICitizen.java"
public interface com.minecolonies.api.colony.ICitizen {
  public abstract int getId();
  public abstract java.lang.String getName();
  public abstract boolean isFemale();
  public abstract double getSaturation();
  public abstract boolean isChild();
  public abstract com.minecolonies.api.inventory.InventoryCitizen getInventory();
  public abstract void setPaused(boolean);
  public abstract boolean isPaused();
  public abstract com.minecolonies.api.colony.IColony getColony();
}
