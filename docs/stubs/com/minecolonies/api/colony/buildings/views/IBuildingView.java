Compiled from "IBuildingView.java"
public interface com.minecolonies.api.colony.buildings.views.IBuildingView extends com.minecolonies.api.colony.requestsystem.requester.IRequester,com.minecolonies.api.colony.buildings.views.IModuleContainerView,com.minecolonies.api.colony.buildings.ICommonBuilding {
  public abstract net.minecraft.core.BlockPos getID();
  public abstract net.minecraft.core.BlockPos getParent();
  public abstract int getBuildingMaxLevel();
  public abstract boolean isBuildingMaxLevel();
  public abstract int getCurrentWorkOrderLevel();
  public abstract java.lang.String getStructurePath();
  public abstract java.lang.String getCustomName();
  public default java.lang.String getBuildingDisplayName();
  public abstract java.lang.String getStructurePack();
  public abstract com.ldtteam.structurize.api.RotationMirror getRotationMirror();
  public abstract boolean hasWorkOrder();
  public abstract boolean isBuilding();
  public abstract boolean isRepairing();
  public abstract boolean isDeconstructing();
  public abstract int getClaimRadius();
  public abstract void openGui(boolean);
  public abstract com.ldtteam.blockui.views.BOWindow getWindow();
  public abstract void deserialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract java.util.Map<java.lang.Integer, java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>>> getOpenRequestsByCitizen();
  public abstract <R> com.google.common.collect.ImmutableList<com.minecolonies.api.colony.requestsystem.request.IRequest<? extends R>> getOpenRequestsOfType(com.minecolonies.api.colony.ICitizenDataView, java.lang.Class<R>);
  public abstract com.google.common.collect.ImmutableList<com.minecolonies.api.colony.requestsystem.request.IRequest<?>> getOpenRequests(com.minecolonies.api.colony.ICitizenDataView);
  public abstract com.google.common.collect.ImmutableList<com.minecolonies.api.colony.requestsystem.request.IRequest<?>> getOpenRequestsOfBuilding();
  public abstract com.minecolonies.api.colony.IColonyView getColony();
  public abstract <R> com.google.common.collect.ImmutableList<com.minecolonies.api.colony.requestsystem.request.IRequest<? extends R>> getOpenRequestsOfTypeFiltered(com.minecolonies.api.colony.ICitizenDataView, java.lang.Class<R>, java.util.function.Predicate<com.minecolonies.api.colony.requestsystem.request.IRequest<? extends R>>);
  public abstract int getBuildingDmPrio();
  public abstract com.google.common.collect.ImmutableCollection<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getResolverIds();
  public abstract void setCustomName(java.lang.String);
  public abstract boolean isDeconstructed();
  public abstract void setBuildingType(com.minecolonies.api.colony.buildings.registry.BuildingEntry);
  public abstract java.util.Set<java.lang.Integer> getAllAssignedCitizens();
  public abstract boolean allowsAssignment();
  public default int getRange();
  public default com.minecolonies.api.colony.IColony getColony();
}
