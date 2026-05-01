Compiled from "DefaultPermissionHandler.java"
public final class net.neoforged.neoforge.server.permission.handler.DefaultPermissionHandler implements net.neoforged.neoforge.server.permission.handler.IPermissionHandler {
  public static final net.minecraft.resources.ResourceLocation IDENTIFIER;
  public net.neoforged.neoforge.server.permission.handler.DefaultPermissionHandler(java.util.Collection<net.neoforged.neoforge.server.permission.nodes.PermissionNode<?>>);
  public net.minecraft.resources.ResourceLocation getIdentifier();
  public java.util.Set<net.neoforged.neoforge.server.permission.nodes.PermissionNode<?>> getRegisteredNodes();
  public <T> T getPermission(net.minecraft.server.level.ServerPlayer, net.neoforged.neoforge.server.permission.nodes.PermissionNode<T>, net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContext<?>...);
  public <T> T getOfflinePermission(java.util.UUID, net.neoforged.neoforge.server.permission.nodes.PermissionNode<T>, net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContext<?>...);
}
