Compiled from "ProjectileImpactEvent.java"
public class net.neoforged.neoforge.event.entity.ProjectileImpactEvent extends net.neoforged.neoforge.event.entity.EntityEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.ProjectileImpactEvent(net.minecraft.world.entity.projectile.Projectile, net.minecraft.world.phys.HitResult);
  public net.minecraft.world.phys.HitResult getRayTraceResult();
  public net.minecraft.world.entity.projectile.Projectile getProjectile();
}
