Compiled from "AbstractTileEntityPlantationField.java"
public abstract class com.minecolonies.api.tileentities.AbstractTileEntityPlantationField extends net.minecraft.world.level.block.entity.BlockEntity implements com.ldtteam.structurize.blockentities.interfaces.IBlueprintDataProviderBE,com.ldtteam.structurize.api.IRotatableBlockEntity {
  public abstract java.util.Set<com.minecolonies.api.colony.buildingextensions.registry.BuildingExtensionRegistries$BuildingExtensionEntry> getPlantationFieldTypes();
  public abstract java.util.List<net.minecraft.core.BlockPos> getWorkingPositions(java.lang.String);
  public abstract com.minecolonies.api.colony.IColony getCurrentColony();
  public abstract net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level> getDimension();
  public abstract net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket getUpdatePacket();
  public abstract com.ldtteam.structurize.api.RotationMirror getRotationMirror();
  public net.minecraft.network.protocol.Packet getUpdatePacket();
}
