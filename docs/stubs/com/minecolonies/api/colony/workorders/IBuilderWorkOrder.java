Compiled from "IBuilderWorkOrder.java"
public interface com.minecolonies.api.colony.workorders.IBuilderWorkOrder extends com.minecolonies.api.colony.workorders.IServerWorkOrder {
  public abstract int getAmountOfResources();
  public abstract void setAmountOfResources(int);
  public abstract java.lang.String getIteratorType();
  public abstract void setIteratorType(java.lang.String);
  public abstract boolean isCleared();
  public abstract void setCleared(boolean);
  public abstract void setRequested(boolean);
  public abstract boolean isRequested();
  public abstract void onCompleted(com.minecolonies.api.colony.IColony, com.minecolonies.api.colony.ICitizenData);
  public abstract void onRemoved(com.minecolonies.api.colony.IColony);
  public abstract boolean canBeResolved(com.minecolonies.api.colony.IColony, int);
  public abstract boolean tooFarFromAnyBuilder(com.minecolonies.api.colony.IColony, int);
  public abstract boolean canBuildIgnoringDistance(com.minecolonies.api.colony.buildings.IBuilding, net.minecraft.core.BlockPos, int);
  public abstract void setStage(com.minecolonies.core.entity.ai.workers.util.BuildingProgressStage);
}
