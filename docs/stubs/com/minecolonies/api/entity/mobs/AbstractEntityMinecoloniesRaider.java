Compiled from "AbstractEntityMinecoloniesRaider.java"
public abstract class com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesRaider extends com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster implements com.minecolonies.api.entity.ai.combat.threat.IThreatTableEntity,net.minecraft.world.entity.monster.Enemy {
  public com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesRaider(net.minecraft.world.entity.EntityType<? extends com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesRaider>, net.minecraft.world.level.Level);
  public com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesRaider(net.minecraft.world.entity.EntityType<? extends com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesRaider>, net.minecraft.world.level.Level, int);
  public com.minecolonies.core.entity.pathfinding.navigation.AbstractAdvancedPathNavigate getNavigation();
  public boolean removeWhenFarAway(double);
  public abstract com.minecolonies.api.entity.mobs.RaiderType getRaiderType();
  public void addAdditionalSaveData(net.minecraft.nbt.CompoundTag);
  public net.minecraft.world.entity.Entity changeDimension(net.minecraft.world.level.portal.DimensionTransition);
  public void readAdditionalSaveData(net.minecraft.nbt.CompoundTag);
  public void aiStep();
  public net.minecraft.world.entity.SpawnGroupData finalizeSpawn(net.minecraft.world.level.ServerLevelAccessor, net.minecraft.world.DifficultyInstance, net.minecraft.world.entity.MobSpawnType, net.minecraft.world.entity.SpawnGroupData);
  public void remove(net.minecraft.world.entity.Entity$RemovalReason);
  public com.minecolonies.api.colony.IColony getColony();
  public void registerWithColony();
  public void die(net.minecraft.world.damagesource.DamageSource);
  public boolean hurt(net.minecraft.world.damagesource.DamageSource, float);
  public void setColony(com.minecolonies.api.colony.IColony);
  public int getEventID();
  public void setEventID(int);
  public void setTempEnvDamageImmunity(boolean);
  public void initStatsFor(double, double, double);
  public double getDifficulty();
  public int getTeamId();
  public net.minecraft.world.entity.ai.navigation.PathNavigation getNavigation();
}
