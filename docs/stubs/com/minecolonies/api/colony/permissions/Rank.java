Compiled from "Rank.java"
public class com.minecolonies.api.colony.permissions.Rank {
  public com.minecolonies.api.colony.permissions.Rank(int, long, java.lang.String, boolean, boolean, boolean);
  public com.minecolonies.api.colony.permissions.Rank(int, java.lang.String, boolean);
  public int getId();
  public java.lang.String getName();
  public boolean isInitial();
  public boolean isColonyManager();
  public boolean isHostile();
  public void setColonyManager(boolean);
  public void setHostile(boolean);
  public boolean equals(java.lang.Object);
  public int hashCode();
  public int compareTo(com.minecolonies.api.colony.permissions.Rank);
  public boolean addPermission(com.minecolonies.api.colony.permissions.Action);
  public boolean removePermission(com.minecolonies.api.colony.permissions.Action);
  public long getPermissions();
}
