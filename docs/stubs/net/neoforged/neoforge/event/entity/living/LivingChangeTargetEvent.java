Compiled from "LivingChangeTargetEvent.java"
public class net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent(net.minecraft.world.entity.LivingEntity, net.minecraft.world.entity.LivingEntity, net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent$ILivingTargetType);
  public net.minecraft.world.entity.LivingEntity getNewAboutToBeSetTarget();
  public void setNewAboutToBeSetTarget(net.minecraft.world.entity.LivingEntity);
  public net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent$ILivingTargetType getTargetType();
  public net.minecraft.world.entity.LivingEntity getOriginalAboutToBeSetTarget();
}
