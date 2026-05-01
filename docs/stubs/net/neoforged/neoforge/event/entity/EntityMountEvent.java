Compiled from "EntityMountEvent.java"
public class net.neoforged.neoforge.event.entity.EntityMountEvent extends net.neoforged.neoforge.event.entity.EntityEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.EntityMountEvent(net.minecraft.world.entity.Entity, net.minecraft.world.entity.Entity, net.minecraft.world.level.Level, boolean);
  public boolean isMounting();
  public boolean isDismounting();
  public net.minecraft.world.entity.Entity getEntityMounting();
  public net.minecraft.world.entity.Entity getEntityBeingMounted();
  public net.minecraft.world.level.Level getLevel();
}
