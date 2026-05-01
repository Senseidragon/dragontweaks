Compiled from "BabyEntitySpawnEvent.java"
public class net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent(net.minecraft.world.entity.Mob, net.minecraft.world.entity.Mob, net.minecraft.world.entity.AgeableMob);
  public net.minecraft.world.entity.Mob getParentA();
  public net.minecraft.world.entity.Mob getParentB();
  public net.minecraft.world.entity.player.Player getCausedByPlayer();
  public net.minecraft.world.entity.AgeableMob getChild();
  public void setChild(net.minecraft.world.entity.AgeableMob);
}
