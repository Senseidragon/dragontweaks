Compiled from "PlayerNegotiationEvent.java"
public class net.neoforged.neoforge.event.entity.player.PlayerNegotiationEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.entity.player.PlayerNegotiationEvent(net.minecraft.network.Connection, com.mojang.authlib.GameProfile, java.util.List<java.util.concurrent.Future<java.lang.Void>>);
  public void enqueueWork(java.lang.Runnable);
  public void enqueueWork(java.util.concurrent.Future<java.lang.Void>);
  public net.minecraft.network.Connection getConnection();
  public com.mojang.authlib.GameProfile getProfile();
}
