Compiled from "ServerLifecycleHooks.java"
public class net.neoforged.neoforge.server.ServerLifecycleHooks {
  public net.neoforged.neoforge.server.ServerLifecycleHooks();
  public static void handleServerAboutToStart(net.minecraft.server.MinecraftServer);
  public static void handleServerStarting(net.minecraft.server.MinecraftServer);
  public static void handleServerStarted(net.minecraft.server.MinecraftServer);
  public static void handleServerStopping(net.minecraft.server.MinecraftServer);
  public static void expectServerStopped();
  public static void handleServerStopped(net.minecraft.server.MinecraftServer);
  public static net.minecraft.server.MinecraftServer getCurrentServer();
  public static void handleExit(int);
}
