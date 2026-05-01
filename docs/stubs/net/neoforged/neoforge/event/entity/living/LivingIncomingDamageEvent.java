Compiled from "LivingIncomingDamageEvent.java"
public class net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent(net.minecraft.world.entity.LivingEntity, net.neoforged.neoforge.common.damagesource.DamageContainer);
  public net.neoforged.neoforge.common.damagesource.DamageContainer getContainer();
  public net.minecraft.world.damagesource.DamageSource getSource();
  public float getAmount();
  public float getOriginalAmount();
  public void setAmount(float);
  public void addReductionModifier(net.neoforged.neoforge.common.damagesource.DamageContainer$Reduction, net.neoforged.neoforge.common.damagesource.IReductionFunction);
  public void setInvulnerabilityTicks(int);
}
