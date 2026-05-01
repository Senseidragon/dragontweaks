Compiled from "MobSplitEvent.java"
public class net.neoforged.neoforge.event.entity.living.MobSplitEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.MobSplitEvent(net.minecraft.world.entity.Mob, java.util.List<net.minecraft.world.entity.Mob>);
  public net.minecraft.world.entity.Mob getParent();
  public java.util.List<net.minecraft.world.entity.Mob> getChildren();
  public void setCanceled(boolean);
}
