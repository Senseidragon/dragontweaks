Compiled from "VanillaGameEvent.java"
public class net.neoforged.neoforge.event.VanillaGameEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.VanillaGameEvent(net.minecraft.world.level.Level, net.minecraft.core.Holder<net.minecraft.world.level.gameevent.GameEvent>, net.minecraft.world.phys.Vec3, net.minecraft.world.level.gameevent.GameEvent$Context);
  public net.minecraft.world.level.Level getLevel();
  public net.minecraft.world.entity.Entity getCause();
  public net.minecraft.core.Holder<net.minecraft.world.level.gameevent.GameEvent> getVanillaEvent();
  public net.minecraft.world.phys.Vec3 getEventPosition();
  public net.minecraft.world.level.gameevent.GameEvent$Context getContext();
}
