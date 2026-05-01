Compiled from "RegisterCommandsEvent.java"
public class net.neoforged.neoforge.event.RegisterCommandsEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.RegisterCommandsEvent(com.mojang.brigadier.CommandDispatcher<net.minecraft.commands.CommandSourceStack>, net.minecraft.commands.Commands$CommandSelection, net.minecraft.commands.CommandBuildContext);
  public com.mojang.brigadier.CommandDispatcher<net.minecraft.commands.CommandSourceStack> getDispatcher();
  public net.minecraft.commands.Commands$CommandSelection getCommandSelection();
  public net.minecraft.commands.CommandBuildContext getBuildContext();
}
