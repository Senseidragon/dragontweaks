Compiled from "IBuildingWorkerView.java"
public interface com.minecolonies.api.colony.buildings.IBuildingWorkerView extends com.minecolonies.api.colony.buildings.views.IBuildingView {
  public abstract java.util.List<java.lang.Integer> getWorkerId();
  public abstract void addWorkerId(int);
  public abstract com.minecolonies.api.entity.citizen.Skill getPrimarySkill();
  public abstract com.minecolonies.api.entity.citizen.Skill getSecondarySkill();
  public abstract void removeWorkerId(int);
  public abstract boolean hasEnoughWorkers();
  public abstract void setHiringMode(com.minecolonies.api.colony.buildings.HiringMode);
  public abstract java.lang.String getJobName();
  public abstract java.lang.String getJobDisplayName();
}
