Compiled from "IAssignsCitizen.java"
public interface com.minecolonies.api.colony.buildings.modules.IAssignsCitizen extends com.minecolonies.api.colony.buildings.modules.IBuildingModule {
  public abstract boolean removeCitizen(com.minecolonies.api.colony.ICitizenData);
  public abstract boolean assignCitizen(com.minecolonies.api.colony.ICitizenData);
  public abstract java.util.List<com.minecolonies.api.colony.ICitizenData> getAssignedCitizen();
  public abstract boolean isFull();
  public abstract int getModuleMax();
  public abstract boolean hasAssignedCitizen(com.minecolonies.api.colony.ICitizenData);
  public abstract java.util.List<java.util.Optional<com.minecolonies.api.entity.citizen.AbstractEntityCitizen>> getAssignedEntities();
  public abstract boolean hasAssignedCitizen();
  public abstract void setHiringMode(com.minecolonies.api.colony.buildings.HiringMode);
  public abstract com.minecolonies.api.colony.buildings.HiringMode getHiringMode();
}
