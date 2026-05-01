Compiled from "ExplosionKnockbackEvent.java"
public class net.neoforged.neoforge.event.level.ExplosionKnockbackEvent extends net.neoforged.neoforge.event.level.ExplosionEvent {
  public net.neoforged.neoforge.event.level.ExplosionKnockbackEvent(net.minecraft.world.level.Level, net.minecraft.world.level.Explosion, net.minecraft.world.entity.Entity, net.minecraft.world.phys.Vec3);
  public java.util.List<net.minecraft.core.BlockPos> getAffectedBlocks();
  public net.minecraft.world.entity.Entity getAffectedEntity();
  public net.minecraft.world.phys.Vec3 getKnockbackVelocity();
  public void setKnockbackVelocity(net.minecraft.world.phys.Vec3);
}
