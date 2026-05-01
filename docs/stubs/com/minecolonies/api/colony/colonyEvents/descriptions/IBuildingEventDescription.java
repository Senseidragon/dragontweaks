Compiled from "IBuildingEventDescription.java"
public interface com.minecolonies.api.colony.colonyEvents.descriptions.IBuildingEventDescription extends com.minecolonies.api.colony.colonyEvents.descriptions.IColonyEventDescription {
  public abstract java.lang.String getBuildingName();
  public abstract void setBuildingName(java.lang.String);
  public abstract int getLevel();
  public abstract void setLevel(int);
  public default java.lang.String toDisplayString();
}
