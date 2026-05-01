Compiled from "SweepAttackEvent.java"
public class net.neoforged.neoforge.event.entity.player.SweepAttackEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.player.SweepAttackEvent(net.minecraft.world.entity.player.Player, net.minecraft.world.entity.Entity, boolean);
  public net.minecraft.world.entity.Entity getTarget();
  public boolean isVanillaSweep();
  public boolean isSweeping();
  public void setSweeping(boolean);
  public void setCanceled(boolean);
}
