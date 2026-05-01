Compiled from "PlayerLeavingModEvent.java"
public final class com.minecolonies.api.eventbus.events.colony.permissions.PlayerLeavingModEvent extends com.minecolonies.api.eventbus.events.colony.AbstractColonyModEvent {
  public com.minecolonies.api.eventbus.events.colony.permissions.PlayerLeavingModEvent(com.minecolonies.api.colony.IColony, net.minecraft.world.entity.player.Player);
  public net.minecraft.world.entity.player.Player getPlayer();
  public boolean shouldShowNotification();
  public void disableNotification();
  public void allowForSpectators();
}
