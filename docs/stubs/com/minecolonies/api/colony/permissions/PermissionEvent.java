Compiled from "PermissionEvent.java"
public class com.minecolonies.api.colony.permissions.PermissionEvent {
  public com.minecolonies.api.colony.permissions.PermissionEvent(java.util.UUID, java.lang.String, com.minecolonies.api.colony.permissions.Action, net.minecraft.core.BlockPos);
  public com.minecolonies.api.colony.permissions.PermissionEvent(net.minecraft.network.RegistryFriendlyByteBuf);
  public java.util.UUID getId();
  public java.lang.String getName();
  public com.minecolonies.api.colony.permissions.Action getAction();
  public net.minecraft.core.BlockPos getPosition();
  public void serialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public boolean equals(java.lang.Object);
  public int hashCode();
}
