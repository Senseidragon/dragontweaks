Compiled from "FinalizeSpawnEvent.java"
public class net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent extends net.neoforged.neoforge.event.entity.living.MobSpawnEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent(net.minecraft.world.entity.Mob, net.minecraft.world.level.ServerLevelAccessor, double, double, double, net.minecraft.world.DifficultyInstance, net.minecraft.world.entity.MobSpawnType, net.minecraft.world.entity.SpawnGroupData, com.mojang.datafixers.util.Either<net.minecraft.world.level.block.entity.BlockEntity, net.minecraft.world.entity.Entity>);
  public net.minecraft.world.DifficultyInstance getDifficulty();
  public void setDifficulty(net.minecraft.world.DifficultyInstance);
  public net.minecraft.world.entity.MobSpawnType getSpawnType();
  public net.minecraft.world.entity.SpawnGroupData getSpawnData();
  public void setSpawnData(net.minecraft.world.entity.SpawnGroupData);
  public com.mojang.datafixers.util.Either<net.minecraft.world.level.block.entity.BlockEntity, net.minecraft.world.entity.Entity> getSpawner();
  public void setSpawnCancelled(boolean);
  public boolean isSpawnCancelled();
}
