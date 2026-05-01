Compiled from "ArrowLooseEvent.java"
public class net.neoforged.neoforge.event.entity.player.ArrowLooseEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.player.ArrowLooseEvent(net.minecraft.world.entity.player.Player, net.minecraft.world.item.ItemStack, net.minecraft.world.level.Level, int, boolean);
  public net.minecraft.world.item.ItemStack getBow();
  public net.minecraft.world.level.Level getLevel();
  public boolean hasAmmo();
  public int getCharge();
  public void setCharge(int);
}
