Compiled from "LivingExperienceDropEvent.java"
public class net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent(net.minecraft.world.entity.LivingEntity, net.minecraft.world.entity.player.Player, int);
  public int getDroppedExperience();
  public void setDroppedExperience(int);
  public net.minecraft.world.entity.player.Player getAttackingPlayer();
  public int getOriginalExperience();
}
