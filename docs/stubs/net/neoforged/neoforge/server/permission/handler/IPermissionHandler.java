Compiled from "IPermissionHandler.java"
public interface net.neoforged.neoforge.server.permission.handler.IPermissionHandler {
  public abstract net.minecraft.resources.ResourceLocation getIdentifier();
  public abstract java.util.Set<net.neoforged.neoforge.server.permission.nodes.PermissionNode<?>> getRegisteredNodes();
  public abstract <T> T getPermission(net.minecraft.server.level.ServerPlayer, net.neoforged.neoforge.server.permission.nodes.PermissionNode<T>, net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContext<?>...);
  public abstract <T> T getOfflinePermission(java.util.UUID, net.neoforged.neoforge.server.permission.nodes.PermissionNode<T>, net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContext<?>...);
}
