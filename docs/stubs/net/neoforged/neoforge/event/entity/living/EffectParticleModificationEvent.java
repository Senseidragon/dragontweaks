Compiled from "EffectParticleModificationEvent.java"
public class net.neoforged.neoforge.event.entity.living.EffectParticleModificationEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent {
  public net.neoforged.neoforge.event.entity.living.EffectParticleModificationEvent(net.minecraft.world.entity.LivingEntity, net.minecraft.world.effect.MobEffectInstance);
  public net.minecraft.world.effect.MobEffectInstance getEffect();
  public net.minecraft.core.particles.ParticleOptions getOriginalParticleOptions();
  public net.minecraft.core.particles.ParticleOptions getParticleOptions();
  public void setParticleOptions(net.minecraft.core.particles.ParticleOptions);
  public boolean isVisible();
  public void setVisible(boolean);
}
