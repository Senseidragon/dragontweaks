Compiled from "AbstractFastMinecoloniesEntity.java"
public abstract class com.minecolonies.api.entity.other.AbstractFastMinecoloniesEntity extends net.minecraft.world.entity.PathfinderMob implements com.minecolonies.api.entity.pathfinding.IStuckHandlerEntity {
  public final int randomVariance;
  public boolean canBeLeashed();
  public boolean canBeStuck();
  public void setCanBeStuck(boolean);
  public boolean hadHorizontalCollission();
  public boolean checkBedExists();
  public void onInsideBubbleColumn(boolean);
  public void pushEntities();
  public net.minecraft.world.entity.Entity changeDimension(net.minecraft.world.level.portal.DimensionTransition);
  public boolean canSpawnSprintParticle();
  public void updateFluidOnEyes();
  public void setSharedFlagOnFire(boolean);
  public void updateSwimming();
  public boolean isInWall();
  public boolean isInWaterRainOrBubble();
  public void updateFallFlying();
  public void setTicksFrozen(int);
  public void updateSwimAmount();
  public void setShiftKeyDown(boolean);
  public boolean isShiftKeyDown();
  public void knockback(double, double, double);
  public boolean hurt(net.minecraft.world.damagesource.DamageSource, float);
  public abstract int getTeamId();
}
