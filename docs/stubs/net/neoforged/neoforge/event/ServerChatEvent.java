Compiled from "ServerChatEvent.java"
public class net.neoforged.neoforge.event.ServerChatEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.ServerChatEvent(net.minecraft.server.level.ServerPlayer, java.lang.String, net.minecraft.network.chat.Component);
  public net.minecraft.server.level.ServerPlayer getPlayer();
  public java.lang.String getUsername();
  public java.lang.String getRawText();
  public void setMessage(net.minecraft.network.chat.Component);
  public net.minecraft.network.chat.Component getMessage();
}
