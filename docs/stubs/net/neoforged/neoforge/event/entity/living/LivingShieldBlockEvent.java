Compiled from "LivingShieldBlockEvent.java"
public class net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent(net.minecraft.world.entity.LivingEntity, net.neoforged.neoforge.common.damagesource.DamageContainer, boolean);
  public net.neoforged.neoforge.common.damagesource.DamageContainer getDamageContainer();
  public net.minecraft.world.damagesource.DamageSource getDamageSource();
  public float getOriginalBlockedDamage();
  public float getBlockedDamage();
  public float shieldDamage();
  public void setBlockedDamage(float);
  public void setShieldDamage(float);
  public boolean getOriginalBlock();
  public boolean getBlocked();
  public void setBlocked(boolean);
}
