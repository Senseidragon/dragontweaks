Compiled from "ITownHallView.java"
public interface com.minecolonies.api.colony.buildings.workerbuildings.ITownHallView extends com.minecolonies.api.colony.buildings.views.IBuildingView {
  public abstract java.util.List<com.minecolonies.api.colony.permissions.PermissionEvent> getPermissionEvents();
  public abstract java.util.List<com.minecolonies.api.colony.colonyEvents.descriptions.IColonyEventDescription> getColonyEvents();
  public abstract boolean canPlayerUseTP();
  public abstract java.util.List<com.minecolonies.api.colony.buildings.workerbuildings.ITownHallView$MapEntry> getMapDataList();
}
