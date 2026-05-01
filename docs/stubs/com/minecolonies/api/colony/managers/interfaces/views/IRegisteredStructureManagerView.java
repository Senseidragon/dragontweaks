Compiled from "IRegisteredStructureManagerView.java"
public interface com.minecolonies.api.colony.managers.interfaces.views.IRegisteredStructureManagerView extends com.minecolonies.api.colony.managers.interfaces.ICommonRegisteredStructureManager<com.minecolonies.api.colony.buildings.views.IBuildingView, com.minecolonies.api.colony.buildings.workerbuildings.ITownHallView> {
  public abstract void handleColonyViewRemoveBuildingMessage(net.minecraft.core.BlockPos);
  public abstract void handleColonyBuildingViewMessage(net.minecraft.core.BlockPos, net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract void handleColonyBuildingExtensionViewUpdateMessage(java.util.Set<com.minecolonies.api.colony.buildingextensions.IBuildingExtension>);
  public abstract void deserializeFromView(boolean, net.minecraft.network.FriendlyByteBuf);
}
