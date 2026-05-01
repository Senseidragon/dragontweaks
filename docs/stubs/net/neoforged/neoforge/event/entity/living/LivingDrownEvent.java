Compiled from "LivingDrownEvent.java"
public class net.neoforged.neoforge.event.entity.living.LivingDrownEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.LivingDrownEvent(net.minecraft.world.entity.LivingEntity, boolean, float, int);
  public net.neoforged.neoforge.event.entity.living.LivingDrownEvent(net.minecraft.world.entity.LivingEntity);
  public boolean isDrowning();
  public void setDrowning(boolean);
  public float getDamageAmount();
  public void setDamageAmount(float);
  public int getBubbleCount();
  public void setBubbleCount(int);
  public void setCanceled(boolean);
}
