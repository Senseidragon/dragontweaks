Compiled from "ItemEntityPickupEvent.java"
public abstract class net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent(net.minecraft.world.entity.player.Player, net.minecraft.world.entity.item.ItemEntity);
  public net.minecraft.world.entity.player.Player getPlayer();
  public net.minecraft.world.entity.item.ItemEntity getItemEntity();
}
