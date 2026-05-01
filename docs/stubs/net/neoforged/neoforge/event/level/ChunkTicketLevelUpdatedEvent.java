Compiled from "ChunkTicketLevelUpdatedEvent.java"
public class net.neoforged.neoforge.event.level.ChunkTicketLevelUpdatedEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.level.ChunkTicketLevelUpdatedEvent(net.minecraft.server.level.ServerLevel, long, int, int, net.minecraft.server.level.ChunkHolder);
  public net.minecraft.server.level.ServerLevel getLevel();
  public long getChunkPos();
  public int getOldTicketLevel();
  public int getNewTicketLevel();
  public net.minecraft.server.level.ChunkHolder getChunkHolder();
}
