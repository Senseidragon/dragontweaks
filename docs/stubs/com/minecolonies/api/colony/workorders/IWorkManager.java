Compiled from "IWorkManager.java"
public interface com.minecolonies.api.colony.workorders.IWorkManager {
  public abstract void removeWorkOrder(com.minecolonies.api.colony.workorders.IServerWorkOrder);
  public abstract void removeWorkOrder(int);
  public abstract <W extends com.minecolonies.api.colony.workorders.IServerWorkOrder> W getWorkOrder(int, java.lang.Class<W>);
  public abstract com.minecolonies.api.colony.workorders.IServerWorkOrder getWorkOrder(int);
  public abstract <W extends com.minecolonies.api.colony.workorders.IServerWorkOrder> W getUnassignedWorkOrder(java.lang.Class<W>);
  public abstract <W extends com.minecolonies.api.colony.workorders.IServerWorkOrder> java.util.List<W> getWorkOrdersOfType(java.lang.Class<W>);
  public abstract java.util.Map<java.lang.Integer, com.minecolonies.api.colony.workorders.IServerWorkOrder> getWorkOrders();
  public abstract void clearWorkForCitizen(com.minecolonies.api.colony.ICitizenData);
  public abstract void write(net.minecraft.nbt.CompoundTag);
  public abstract void read(net.minecraft.nbt.CompoundTag);
  public abstract void addWorkOrder(com.minecolonies.api.colony.workorders.IServerWorkOrder, boolean);
  public abstract void onColonyTick(com.minecolonies.api.colony.IColony);
  public abstract <W extends com.minecolonies.api.colony.workorders.IServerWorkOrder> java.util.List<W> getOrderedList(java.lang.Class<W>, net.minecraft.core.BlockPos);
  public abstract java.util.List<com.minecolonies.api.colony.workorders.IServerWorkOrder> getOrderedList(java.util.function.Predicate<com.minecolonies.api.colony.workorders.IServerWorkOrder>, net.minecraft.core.BlockPos);
  public abstract boolean isDirty();
  public abstract void setDirty(boolean);
  public abstract com.minecolonies.api.colony.IColony getColony();
}
