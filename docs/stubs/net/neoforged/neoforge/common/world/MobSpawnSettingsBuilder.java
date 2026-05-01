Compiled from "MobSpawnSettingsBuilder.java"
public class net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder extends net.minecraft.world.level.biome.MobSpawnSettings$Builder {
  public net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder(net.minecraft.world.level.biome.MobSpawnSettings);
  public java.util.Set<net.minecraft.world.entity.MobCategory> getSpawnerTypes();
  public java.util.List<net.minecraft.world.level.biome.MobSpawnSettings$SpawnerData> getSpawner(net.minecraft.world.entity.MobCategory);
  public java.util.Set<net.minecraft.world.entity.EntityType<?>> getEntityTypes();
  public net.minecraft.world.level.biome.MobSpawnSettings$MobSpawnCost getCost(net.minecraft.world.entity.EntityType<?>);
  public float getProbability();
  public net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder disablePlayerSpawn();
  public net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder removeSpawnCost(net.minecraft.world.entity.EntityType<?>...);
}
