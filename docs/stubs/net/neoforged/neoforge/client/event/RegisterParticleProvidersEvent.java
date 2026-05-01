Compiled from "RegisterParticleProvidersEvent.java"
public class net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent(net.minecraft.client.particle.ParticleEngine);
  public <T extends net.minecraft.core.particles.ParticleOptions> void registerSpecial(net.minecraft.core.particles.ParticleType<T>, net.minecraft.client.particle.ParticleProvider<T>);
  public <T extends net.minecraft.core.particles.ParticleOptions> void registerSprite(net.minecraft.core.particles.ParticleType<T>, net.minecraft.client.particle.ParticleProvider$Sprite<T>);
  public <T extends net.minecraft.core.particles.ParticleOptions> void registerSpriteSet(net.minecraft.core.particles.ParticleType<T>, net.minecraft.client.particle.ParticleEngine$SpriteParticleRegistration<T>);
}
