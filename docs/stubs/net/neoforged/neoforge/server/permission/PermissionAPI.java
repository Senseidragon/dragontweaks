Compiled from "PermissionAPI.java"
public final class net.neoforged.neoforge.server.permission.PermissionAPI {
  public static java.util.Collection<net.neoforged.neoforge.server.permission.nodes.PermissionNode<?>> getRegisteredNodes();
  public static net.minecraft.resources.ResourceLocation getActivePermissionHandler();
  public static <T> T getPermission(net.minecraft.server.level.ServerPlayer, net.neoforged.neoforge.server.permission.nodes.PermissionNode<T>, net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContext<?>...);
  public static <T> T getOfflinePermission(java.util.UUID, net.neoforged.neoforge.server.permission.nodes.PermissionNode<T>, net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContext<?>...);
  public static void initializePermissionAPI();
}
