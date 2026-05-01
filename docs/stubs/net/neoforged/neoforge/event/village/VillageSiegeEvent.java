Compiled from "VillageSiegeEvent.java"
public class net.neoforged.neoforge.event.village.VillageSiegeEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.village.VillageSiegeEvent(net.minecraft.world.entity.ai.village.VillageSiege, net.minecraft.world.level.Level, net.minecraft.world.entity.player.Player, net.minecraft.world.phys.Vec3);
  public net.minecraft.world.entity.ai.village.VillageSiege getSiege();
  public net.minecraft.world.level.Level getLevel();
  public net.minecraft.world.entity.player.Player getPlayer();
  public net.minecraft.world.phys.Vec3 getAttemptedSpawnPos();
}
