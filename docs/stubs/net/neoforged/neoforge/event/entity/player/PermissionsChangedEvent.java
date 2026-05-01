Compiled from "PermissionsChangedEvent.java"
public class net.neoforged.neoforge.event.entity.player.PermissionsChangedEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.player.PermissionsChangedEvent(net.minecraft.server.level.ServerPlayer, int, int);
  public int getNewLevel();
  public int getOldLevel();
}
