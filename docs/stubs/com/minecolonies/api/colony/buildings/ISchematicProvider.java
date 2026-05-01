Compiled from "ISchematicProvider.java"
public interface com.minecolonies.api.colony.buildings.ISchematicProvider extends net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public abstract void setCorners(net.minecraft.core.BlockPos, net.minecraft.core.BlockPos);
  public abstract net.minecraft.util.Tuple<net.minecraft.core.BlockPos, net.minecraft.core.BlockPos> getCorners();
  public abstract net.minecraft.core.BlockPos getID();
  public abstract net.minecraft.core.BlockPos getParent();
  public abstract boolean hasParent();
  public abstract void setParent(net.minecraft.core.BlockPos);
  public abstract java.util.Set<net.minecraft.core.BlockPos> getChildren();
  public abstract java.lang.String getStructurePack();
  public abstract void setStructurePack(java.lang.String);
  public abstract java.lang.String getBlueprintPath();
  public abstract void setBlueprintPath(java.lang.String);
  public abstract void setBuildingLevel(int);
  public abstract boolean isDirty();
  public abstract void clearDirty();
  public abstract void markDirty();
  public abstract java.lang.String getSchematicName();
  public abstract int getMaxBuildingLevel();
  public abstract boolean isDeconstructed();
  public abstract void setDeconstructed();
  public abstract void onUpgradeSchematicTo(java.lang.String, java.lang.String, com.ldtteam.structurize.blockentities.interfaces.IBlueprintDataProviderBE);
  public abstract void setRotationMirror(com.ldtteam.structurize.api.RotationMirror);
  public abstract com.ldtteam.structurize.api.RotationMirror getRotationMirror();
}
