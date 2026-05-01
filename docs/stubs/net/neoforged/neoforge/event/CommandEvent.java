Compiled from "CommandEvent.java"
public class net.neoforged.neoforge.event.CommandEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.CommandEvent(com.mojang.brigadier.ParseResults<net.minecraft.commands.CommandSourceStack>);
  public com.mojang.brigadier.ParseResults<net.minecraft.commands.CommandSourceStack> getParseResults();
  public void setParseResults(com.mojang.brigadier.ParseResults<net.minecraft.commands.CommandSourceStack>);
  public java.lang.Throwable getException();
  public void setException(java.lang.Throwable);
}
