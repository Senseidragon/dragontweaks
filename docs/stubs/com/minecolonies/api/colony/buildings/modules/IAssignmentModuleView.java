Compiled from "IAssignmentModuleView.java"
public interface com.minecolonies.api.colony.buildings.modules.IAssignmentModuleView extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView {
  public abstract java.util.List<java.lang.Integer> getAssignedCitizens();
  public abstract void addCitizen(com.minecolonies.api.colony.ICitizenDataView);
  public abstract void removeCitizen(com.minecolonies.api.colony.ICitizenDataView);
  public abstract com.minecolonies.api.colony.buildings.HiringMode getHiringMode();
  public abstract void setHiringMode(com.minecolonies.api.colony.buildings.HiringMode);
  public abstract boolean canAssign(com.minecolonies.api.colony.ICitizenDataView);
  public abstract int getMaxInhabitants();
  public abstract boolean isFull();
  public abstract com.minecolonies.api.colony.jobs.registry.JobEntry getJobEntry();
}
