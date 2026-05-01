Compiled from "PlayerSetSpawnEvent.java"
public class net.neoforged.neoforge.event.entity.player.PlayerSetSpawnEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.player.PlayerSetSpawnEvent(net.minecraft.world.entity.player.Player, net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level>, net.minecraft.core.BlockPos, boolean);
  public boolean isForced();
  public net.minecraft.core.BlockPos getNewSpawn();
  public net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level> getSpawnLevel();
}
