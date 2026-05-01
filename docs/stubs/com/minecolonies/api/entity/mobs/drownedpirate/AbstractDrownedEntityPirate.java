Compiled from "AbstractDrownedEntityPirate.java"
public abstract class com.minecolonies.api.entity.mobs.drownedpirate.AbstractDrownedEntityPirate extends com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster {
  public com.minecolonies.api.entity.mobs.drownedpirate.AbstractDrownedEntityPirate(net.minecraft.world.entity.EntityType<? extends com.minecolonies.api.entity.mobs.drownedpirate.AbstractDrownedEntityPirate>, net.minecraft.world.level.Level);
  public void playAmbientSound();
  public boolean checkSpawnObstruction(net.minecraft.world.level.LevelReader);
  public boolean checkSpawnRules(net.minecraft.world.level.LevelAccessor, net.minecraft.world.entity.MobSpawnType);
  public com.minecolonies.core.entity.pathfinding.navigation.AbstractAdvancedPathNavigate getNavigation();
  public com.minecolonies.api.entity.mobs.RaiderType getRaiderType();
  public double getSwimSpeedFactor();
  public net.minecraft.world.entity.ai.navigation.PathNavigation getNavigation();
}
