Compiled from "ItemFishedEvent.java"
public class net.neoforged.neoforge.event.entity.player.ItemFishedEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.player.ItemFishedEvent(java.util.List<net.minecraft.world.item.ItemStack>, int, net.minecraft.world.entity.projectile.FishingHook);
  public int getRodDamage();
  public void damageRodBy(int);
  public net.minecraft.core.NonNullList<net.minecraft.world.item.ItemStack> getDrops();
  public net.minecraft.world.entity.projectile.FishingHook getHookEntity();
}
