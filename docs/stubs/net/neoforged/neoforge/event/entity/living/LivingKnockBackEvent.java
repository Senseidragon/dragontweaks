Compiled from "LivingKnockBackEvent.java"
public class net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent(net.minecraft.world.entity.LivingEntity, float, double, double);
  public float getStrength();
  public double getRatioX();
  public double getRatioZ();
  public float getOriginalStrength();
  public double getOriginalRatioX();
  public double getOriginalRatioZ();
  public void setStrength(float);
  public void setRatioX(double);
  public void setRatioZ(double);
}
