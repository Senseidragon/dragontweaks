Compiled from "IWorkOrder.java"
public interface com.minecolonies.api.colony.workorders.IWorkOrder {
  public abstract int getID();
  public abstract void setID(int);
  public abstract int getPriority();
  public abstract void setPriority(int);
  public abstract java.lang.String getStructurePath();
  public abstract java.lang.String getStructurePack();
  public abstract void loadBlueprint(net.minecraft.world.level.Level, java.util.function.Consumer<com.ldtteam.structurize.blueprints.v1.Blueprint>);
  public abstract int getCurrentLevel();
  public abstract int getTargetLevel();
  public abstract java.lang.String getTranslationKey();
  public abstract com.minecolonies.api.colony.workorders.WorkOrderType getWorkOrderType();
  public abstract net.minecraft.core.BlockPos getLocation();
  public abstract com.ldtteam.structurize.api.RotationMirror getRotationMirror();
  public abstract boolean isClaimed();
  public abstract net.minecraft.core.BlockPos getClaimedBy();
  public abstract void setClaimedBy(net.minecraft.core.BlockPos);
  public abstract net.minecraft.network.chat.Component getDisplayName();
  public default java.lang.String getFileName();
  public abstract void setBlueprint(com.ldtteam.structurize.blueprints.v1.Blueprint, net.minecraft.world.level.Level);
  public abstract com.ldtteam.structurize.blueprints.v1.Blueprint getBlueprint();
  public abstract void clearBlueprint();
  public abstract net.minecraft.world.phys.AABB getBoundingBox();
  public abstract com.minecolonies.api.colony.IColony getColony();
  public abstract void setColony(com.minecolonies.api.colony.IColony);
  public abstract com.minecolonies.core.entity.ai.workers.util.BuildingProgressStage getStage();
}
