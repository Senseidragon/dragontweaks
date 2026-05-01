Compiled from "IServerWorkOrder.java"
public interface com.minecolonies.api.colony.workorders.IServerWorkOrder extends com.minecolonies.api.colony.workorders.IWorkOrder {
  public abstract boolean isDirty();
  public abstract void resetChange();
  public abstract boolean isValid(com.minecolonies.api.colony.IColony);
  public abstract void read(net.minecraft.nbt.CompoundTag, com.minecolonies.api.colony.workorders.IWorkManager);
  public abstract void write(net.minecraft.nbt.CompoundTag);
  public abstract void serializeViewNetworkData(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract void onAdded(com.minecolonies.api.colony.IColony, boolean);
  public abstract boolean canBuild(com.minecolonies.api.colony.buildings.IBuilding);
}
