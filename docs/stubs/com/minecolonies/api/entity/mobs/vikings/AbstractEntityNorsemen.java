Compiled from "AbstractEntityNorsemen.java"
public abstract class com.minecolonies.api.entity.mobs.vikings.AbstractEntityNorsemen extends com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster {
  public com.minecolonies.api.entity.mobs.vikings.AbstractEntityNorsemen(net.minecraft.world.entity.EntityType<? extends com.minecolonies.api.entity.mobs.vikings.AbstractEntityNorsemen>, net.minecraft.world.level.Level);
  public void playAmbientSound();
  public float getVoicePitch();
  public boolean checkSpawnRules(net.minecraft.world.level.LevelAccessor, net.minecraft.world.entity.MobSpawnType);
  public com.minecolonies.core.entity.pathfinding.navigation.AbstractAdvancedPathNavigate getNavigation();
  public com.minecolonies.api.entity.mobs.RaiderType getRaiderType();
  public double getSwimSpeedFactor();
  public net.minecraft.world.entity.ai.navigation.PathNavigation getNavigation();
}
