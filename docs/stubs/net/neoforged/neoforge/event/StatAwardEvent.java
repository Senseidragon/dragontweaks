Compiled from "StatAwardEvent.java"
public class net.neoforged.neoforge.event.StatAwardEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.StatAwardEvent(net.minecraft.world.entity.player.Player, net.minecraft.stats.Stat<?>, int);
  public net.minecraft.stats.Stat<?> getStat();
  public void setStat(net.minecraft.stats.Stat<?>);
  public int getValue();
  public void setValue(int);
}
