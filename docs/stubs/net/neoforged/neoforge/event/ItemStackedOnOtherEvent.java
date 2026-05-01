Compiled from "ItemStackedOnOtherEvent.java"
public class net.neoforged.neoforge.event.ItemStackedOnOtherEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.ItemStackedOnOtherEvent(net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack, net.minecraft.world.inventory.Slot, net.minecraft.world.inventory.ClickAction, net.minecraft.world.entity.player.Player, net.minecraft.world.entity.SlotAccess);
  public net.minecraft.world.item.ItemStack getCarriedItem();
  public net.minecraft.world.item.ItemStack getStackedOnItem();
  public net.minecraft.world.inventory.Slot getSlot();
  public net.minecraft.world.inventory.ClickAction getClickAction();
  public net.minecraft.world.entity.player.Player getPlayer();
  public net.minecraft.world.entity.SlotAccess getCarriedSlotAccess();
}
