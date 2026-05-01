Compiled from "IBuildingExtension.java"
public interface com.minecolonies.api.colony.buildingextensions.IBuildingExtension extends com.minecolonies.api.colony.modules.IModuleContainer<com.minecolonies.api.colony.buildingextensions.modules.IBuildingExtensionModule> {
  public abstract com.minecolonies.api.colony.buildingextensions.registry.BuildingExtensionRegistries$BuildingExtensionEntry getBuildingExtensionType();
  public abstract net.minecraft.core.BlockPos getPosition();
  public abstract net.minecraft.core.BlockPos getBuildingId();
  public abstract void setBuilding(net.minecraft.core.BlockPos);
  public abstract void resetOwningBuilding();
  public abstract boolean isTaken();
  public abstract int getSqDistance(com.minecolonies.api.colony.buildings.views.IBuildingView);
  public abstract net.minecraft.nbt.CompoundTag serializeNBT(net.minecraft.core.HolderLookup$Provider);
  public abstract void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void serialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract void deserialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract boolean isValidPlacement(com.minecolonies.api.colony.IColony);
  public abstract int hashCode();
  public abstract boolean equals(java.lang.Object);
  public abstract com.minecolonies.api.colony.buildingextensions.IBuildingExtension$ExtensionId getId();
  public abstract void registerModule(com.minecolonies.api.colony.buildingextensions.modules.IBuildingExtensionModule);
}
