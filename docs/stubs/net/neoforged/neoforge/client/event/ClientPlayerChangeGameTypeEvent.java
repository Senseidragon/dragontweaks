Compiled from "ClientPlayerChangeGameTypeEvent.java"
public class net.neoforged.neoforge.client.event.ClientPlayerChangeGameTypeEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.client.event.ClientPlayerChangeGameTypeEvent(net.minecraft.client.multiplayer.PlayerInfo, net.minecraft.world.level.GameType, net.minecraft.world.level.GameType);
  public net.minecraft.client.multiplayer.PlayerInfo getInfo();
  public net.minecraft.world.level.GameType getCurrentGameType();
  public net.minecraft.world.level.GameType getNewGameType();
}
