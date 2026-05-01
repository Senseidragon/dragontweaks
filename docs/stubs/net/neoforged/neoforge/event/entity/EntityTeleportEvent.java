Compiled from "EntityTeleportEvent.java"
public class net.neoforged.neoforge.event.entity.EntityTeleportEvent extends net.neoforged.neoforge.event.entity.EntityEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.EntityTeleportEvent(net.minecraft.world.entity.Entity, double, double, double);
  public double getTargetX();
  public void setTargetX(double);
  public double getTargetY();
  public void setTargetY(double);
  public double getTargetZ();
  public void setTargetZ(double);
  public net.minecraft.world.phys.Vec3 getTarget();
  public double getPrevX();
  public double getPrevY();
  public double getPrevZ();
  public net.minecraft.world.phys.Vec3 getPrev();
}
