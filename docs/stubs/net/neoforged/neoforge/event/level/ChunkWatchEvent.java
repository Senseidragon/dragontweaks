Compiled from "ChunkWatchEvent.java"
public abstract class net.neoforged.neoforge.event.level.ChunkWatchEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.level.ChunkWatchEvent(net.minecraft.server.level.ServerPlayer, net.minecraft.world.level.ChunkPos, net.minecraft.server.level.ServerLevel);
  public net.minecraft.server.level.ServerPlayer getPlayer();
  public net.minecraft.world.level.ChunkPos getPos();
  public net.minecraft.server.level.ServerLevel getLevel();
}
