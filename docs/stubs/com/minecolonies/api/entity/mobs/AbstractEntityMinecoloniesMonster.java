Compiled from "AbstractEntityMinecoloniesMonster.java"
public abstract class com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster extends com.minecolonies.api.entity.other.AbstractFastMinecoloniesEntity implements com.minecolonies.api.entity.ai.combat.threat.IThreatTableEntity,net.minecraft.world.entity.monster.Enemy {
  public com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster(net.minecraft.world.entity.EntityType<? extends com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster>, net.minecraft.world.level.Level);
  public com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster(net.minecraft.world.entity.EntityType<? extends com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster>, net.minecraft.world.level.Level, int);
  public void pushEntities();
  public void push(net.minecraft.world.entity.Entity);
  public void playAmbientSound();
  public abstract com.minecolonies.api.entity.mobs.RaiderType getRaiderType();
  public com.minecolonies.core.entity.pathfinding.navigation.AbstractAdvancedPathNavigate getNavigation();
  public abstract double getSwimSpeedFactor();
  public void initStatsFor(double, double, double);
  public void aiStep();
  public boolean hurt(net.minecraft.world.damagesource.DamageSource, float);
  public static net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder getDefaultAttributes();
  public void addAdditionalSaveData(net.minecraft.nbt.CompoundTag);
  public void readAdditionalSaveData(net.minecraft.nbt.CompoundTag);
  public boolean isPushedByFluid();
  public com.minecolonies.api.entity.ai.combat.threat.ThreatTable getThreatTable();
  public com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickRateStateMachine<com.minecolonies.api.entity.ai.statemachine.states.IState> getAI();
  public int getTeamId();
  public int getTextureId();
  public net.minecraft.core.BlockPos getSpawnPos();
  public double getDifficulty();
  public net.minecraft.world.entity.ai.navigation.PathNavigation getNavigation();
}
