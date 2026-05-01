Compiled from "RegisterClientCommandsEvent.java"
public class net.neoforged.neoforge.client.event.RegisterClientCommandsEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.client.event.RegisterClientCommandsEvent(com.mojang.brigadier.CommandDispatcher<net.minecraft.commands.CommandSourceStack>, net.minecraft.commands.CommandBuildContext);
  public com.mojang.brigadier.CommandDispatcher<net.minecraft.commands.CommandSourceStack> getDispatcher();
  public net.minecraft.commands.CommandBuildContext getBuildContext();
}
