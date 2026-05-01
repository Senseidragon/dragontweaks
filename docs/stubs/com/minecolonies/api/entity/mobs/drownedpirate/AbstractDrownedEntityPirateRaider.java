Compiled from "AbstractDrownedEntityPirateRaider.java"
public abstract class com.minecolonies.api.entity.mobs.drownedpirate.AbstractDrownedEntityPirateRaider extends com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesRaider {
  public com.minecolonies.api.entity.mobs.drownedpirate.AbstractDrownedEntityPirateRaider(net.minecraft.world.entity.EntityType<? extends com.minecolonies.api.entity.mobs.drownedpirate.AbstractDrownedEntityPirateRaider>, net.minecraft.world.level.Level);
  public void playAmbientSound();
  public boolean checkSpawnObstruction(net.minecraft.world.level.LevelReader);
  public boolean checkSpawnRules(net.minecraft.world.level.LevelAccessor, net.minecraft.world.entity.MobSpawnType);
  public com.minecolonies.core.entity.pathfinding.navigation.AbstractAdvancedPathNavigate getNavigation();
  public com.minecolonies.api.entity.mobs.RaiderType getRaiderType();
  public double getSwimSpeedFactor();
  public net.minecraft.world.entity.ai.navigation.PathNavigation getNavigation();
}
