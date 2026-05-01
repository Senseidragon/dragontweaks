Compiled from "ClientPlayerNetworkEvent.java"
public abstract class net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent extends net.neoforged.bus.api.Event {
  public net.minecraft.client.multiplayer.MultiPlayerGameMode getMultiPlayerGameMode();
  public net.minecraft.client.player.LocalPlayer getPlayer();
  public net.minecraft.network.Connection getConnection();
}
