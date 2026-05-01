Compiled from "ICitizenColonyHandler.java"
public interface com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenColonyHandler {
  public abstract com.minecolonies.api.colony.buildings.IBuilding getWorkBuilding();
  public abstract com.minecolonies.api.colony.buildings.IBuilding getHomeBuilding();
  public abstract void registerWithColony(int, int);
  public abstract void updateColonyClient();
  public abstract com.minecolonies.api.colony.IColony getColonyOrRegister();
  public abstract int getColonyId();
  public abstract void setColonyId(int);
  public abstract void onCitizenRemoved();
  public abstract void onSyncDataUpdate(net.minecraft.network.syncher.EntityDataAccessor<?>);
  public abstract boolean registered();
  public abstract com.minecolonies.api.colony.IColony getColony();
}
