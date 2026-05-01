Compiled from "ClientChatReceivedEvent.java"
public class net.neoforged.neoforge.client.event.ClientChatReceivedEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.client.event.ClientChatReceivedEvent(net.minecraft.network.chat.ChatType$Bound, net.minecraft.network.chat.Component, java.util.UUID);
  public net.minecraft.network.chat.Component getMessage();
  public void setMessage(net.minecraft.network.chat.Component);
  public net.minecraft.network.chat.ChatType$Bound getBoundChatType();
  public java.util.UUID getSender();
  public boolean isSystem();
}
