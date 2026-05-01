Compiled from "AbstractDeliverymanRequestable.java"
public abstract class com.minecolonies.api.colony.requestsystem.requestable.deliveryman.AbstractDeliverymanRequestable implements com.minecolonies.api.colony.requestsystem.requestable.deliveryman.IDeliverymanRequestable {
  public static final int MAX_BUILDING_PRIORITY;
  public static int scaledPriority(int);
  public static int getMaxBuildingPriority(boolean);
  public static int getDefaultDeliveryPriority(boolean);
  public static int getMaxAgingPriority(boolean);
  public static int getPlayerActionPriority(boolean);
  public int getPriority();
  public void incrementPriorityDueToAging();
  public boolean equals(java.lang.Object);
  public int hashCode();
}
