Compiled from "AbstractTileEntityColonyBuilding.java"
public abstract class com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding extends com.minecolonies.core.tileentities.TileEntityRack implements com.ldtteam.structurize.blockentities.interfaces.IBlueprintDataProviderBE {
  public com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding(net.minecraft.world.level.block.entity.BlockEntityType<? extends com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding>, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public static boolean isInTileEntity(com.minecolonies.api.util.IItemHandlerCapProvider, java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public abstract int getColonyId();
  public abstract com.minecolonies.api.colony.IColony getColony();
  public abstract void setColony(com.minecolonies.api.colony.IColony);
  public abstract net.minecraft.core.BlockPos getPosition();
  public abstract net.minecraft.core.BlockPos getPositionOfChestWithItemStack(java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public abstract com.minecolonies.api.colony.buildings.IBuilding getBuilding();
  public abstract void setBuilding(com.minecolonies.api.colony.buildings.IBuilding);
  public abstract com.minecolonies.api.colony.buildings.views.IBuildingView getBuildingView();
  public abstract boolean hasAccessPermission(net.minecraft.world.entity.player.Player);
  public abstract void setRotationMirror(com.ldtteam.structurize.api.RotationMirror);
  public abstract com.ldtteam.structurize.api.RotationMirror getRotationMirror();
  public abstract com.ldtteam.structurize.storage.StructurePackMeta getStructurePack();
  public abstract void setStructurePack(com.ldtteam.structurize.storage.StructurePackMeta);
  public abstract void setBlueprintPath(java.lang.String);
  public abstract java.lang.String getBlueprintPath();
  public abstract net.minecraft.resources.ResourceLocation getBuildingName();
  public java.lang.String getSchematicName();
  public void setSchematicName(java.lang.String);
  public java.util.Map<net.minecraft.core.BlockPos, java.util.List<java.lang.String>> getPositionedTags();
  public java.util.Map<java.lang.String, java.util.Set<net.minecraft.core.BlockPos>> getWorldTagNamePosMap();
  public java.util.Map<java.lang.String, java.util.List<net.minecraft.core.BlockPos>> getCachedWorldTagNamePosMap();
  public void setPositionedTags(java.util.Map<net.minecraft.core.BlockPos, java.util.List<java.lang.String>>);
  public net.minecraft.util.Tuple<net.minecraft.core.BlockPos, net.minecraft.core.BlockPos> getSchematicCorners();
  public void setSchematicCorners(net.minecraft.core.BlockPos, net.minecraft.core.BlockPos);
  public void loadAdditional(net.minecraft.nbt.CompoundTag, net.minecraft.core.HolderLookup$Provider);
  public void readSchematicDataFromNBT(net.minecraft.nbt.CompoundTag);
  public void saveAdditional(net.minecraft.nbt.CompoundTag, net.minecraft.core.HolderLookup$Provider);
  public net.minecraft.core.BlockPos getTilePos();
  public boolean isOutdated();
}
