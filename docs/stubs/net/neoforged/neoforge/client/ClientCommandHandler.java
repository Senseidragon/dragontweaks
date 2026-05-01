Compiled from "ClientCommandHandler.java"
public class net.neoforged.neoforge.client.ClientCommandHandler {
  public net.neoforged.neoforge.client.ClientCommandHandler();
  public static void init();
  public static com.mojang.brigadier.CommandDispatcher<net.minecraft.commands.SharedSuggestionProvider> mergeServerCommands(com.mojang.brigadier.CommandDispatcher<net.minecraft.commands.SharedSuggestionProvider>, net.minecraft.commands.CommandBuildContext);
  public static com.mojang.brigadier.CommandDispatcher<net.minecraft.commands.CommandSourceStack> getDispatcher();
  public static net.neoforged.neoforge.client.ClientCommandSourceStack getSource();
  public static boolean runCommand(java.lang.String);
}
