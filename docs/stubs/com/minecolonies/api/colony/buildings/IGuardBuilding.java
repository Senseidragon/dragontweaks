Compiled from "IGuardBuilding.java"
public interface com.minecolonies.api.colony.buildings.IGuardBuilding extends com.minecolonies.api.colony.buildings.IBuilding {
  public static final int PATROL_DISTANCE;
  public static boolean checkIfGuardShouldTakeDamage(com.minecolonies.api.entity.citizen.AbstractEntityCitizen, net.minecraft.world.entity.player.Player);
  public abstract java.lang.String getTask();
  public abstract net.minecraft.core.BlockPos getNextPatrolTarget(boolean);
  public abstract void arrivedAtPatrolPoint(com.minecolonies.api.entity.citizen.AbstractEntityCitizen);
  public abstract int getPatrolDistance();
  public abstract boolean shallRetrieveOnLowHealth();
  public abstract boolean shallPatrolManually();
  public abstract boolean isTightGrouping();
  public abstract net.minecraft.core.BlockPos getGuardPos(com.minecolonies.api.entity.citizen.AbstractEntityCitizen);
  public abstract void setGuardPos(net.minecraft.core.BlockPos);
  public abstract net.minecraft.world.entity.player.Player getPlayerToFollowOrRally();
  public abstract void setPlayerToFollow(net.minecraft.world.entity.player.Player);
  public abstract com.minecolonies.api.colony.requestsystem.location.ILocation getRallyLocation();
  public abstract void setRallyLocation(com.minecolonies.api.colony.requestsystem.location.ILocation);
  public abstract net.minecraft.core.BlockPos getPositionToFollow();
  public abstract void addPatrolTarget(net.minecraft.core.BlockPos);
  public abstract void resetPatrolTargets();
  public abstract int getBonusVision();
  public abstract void calculateMobs();
  public abstract boolean requiresManualTarget();
  public abstract void setTempNextPatrolPoint(net.minecraft.core.BlockPos);
  public abstract net.minecraft.core.BlockPos getMinePos();
}
