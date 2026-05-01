Compiled from "OnDatapackSyncEvent.java"
public class net.neoforged.neoforge.event.OnDatapackSyncEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.OnDatapackSyncEvent(net.minecraft.server.players.PlayerList, net.minecraft.server.level.ServerPlayer);
  public net.minecraft.server.players.PlayerList getPlayerList();
  public java.util.stream.Stream<net.minecraft.server.level.ServerPlayer> getRelevantPlayers();
  public net.minecraft.server.level.ServerPlayer getPlayer();
}
