Compiled from "EntityJoinLevelEvent.java"
public class net.neoforged.neoforge.event.entity.EntityJoinLevelEvent extends net.neoforged.neoforge.event.entity.EntityEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.EntityJoinLevelEvent(net.minecraft.world.entity.Entity, net.minecraft.world.level.Level);
  public net.neoforged.neoforge.event.entity.EntityJoinLevelEvent(net.minecraft.world.entity.Entity, net.minecraft.world.level.Level, boolean);
  public net.minecraft.world.level.Level getLevel();
  public boolean loadedFromDisk();
}
