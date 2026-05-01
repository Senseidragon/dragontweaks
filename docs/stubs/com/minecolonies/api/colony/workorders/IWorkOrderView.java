Compiled from "IWorkOrderView.java"
public interface com.minecolonies.api.colony.workorders.IWorkOrderView extends com.minecolonies.api.colony.workorders.IWorkOrder {
  public abstract boolean shouldShowIn(com.minecolonies.api.colony.buildings.views.IBuildingView);
  public abstract void deserialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract boolean canBuildIgnoringDistance(net.minecraft.core.BlockPos, int);
}
