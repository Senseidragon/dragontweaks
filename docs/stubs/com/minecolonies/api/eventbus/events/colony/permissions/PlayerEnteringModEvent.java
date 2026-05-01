Compiled from "PlayerEnteringModEvent.java"
public final class com.minecolonies.api.eventbus.events.colony.permissions.PlayerEnteringModEvent extends com.minecolonies.api.eventbus.events.colony.AbstractColonyModEvent {
  public com.minecolonies.api.eventbus.events.colony.permissions.PlayerEnteringModEvent(com.minecolonies.api.colony.IColony, net.minecraft.world.entity.player.Player);
  public net.minecraft.world.entity.player.Player getPlayer();
  public boolean shouldShowNotification();
  public void disableNotification();
  public void allowForSpectators();
}
