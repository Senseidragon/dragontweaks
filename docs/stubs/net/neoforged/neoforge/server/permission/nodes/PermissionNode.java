Compiled from "PermissionNode.java"
public final class net.neoforged.neoforge.server.permission.nodes.PermissionNode<T> {
  public net.neoforged.neoforge.server.permission.nodes.PermissionNode(net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.server.permission.nodes.PermissionType<T>, net.neoforged.neoforge.server.permission.nodes.PermissionNode$PermissionResolver<T>, net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContextKey...);
  public net.neoforged.neoforge.server.permission.nodes.PermissionNode(java.lang.String, java.lang.String, net.neoforged.neoforge.server.permission.nodes.PermissionType<T>, net.neoforged.neoforge.server.permission.nodes.PermissionNode$PermissionResolver<T>, net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContextKey...);
  public net.neoforged.neoforge.server.permission.nodes.PermissionNode setInformation(net.minecraft.network.chat.Component, net.minecraft.network.chat.Component);
  public java.lang.String getNodeName();
  public net.neoforged.neoforge.server.permission.nodes.PermissionType<T> getType();
  public net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContextKey<?>[] getDynamics();
  public net.neoforged.neoforge.server.permission.nodes.PermissionNode$PermissionResolver<T> getDefaultResolver();
  public net.minecraft.network.chat.Component getReadableName();
  public net.minecraft.network.chat.Component getDescription();
  public boolean equals(java.lang.Object);
  public int hashCode();
}
