Compiled from "ItemHandlerCopySlot.java"
public class net.neoforged.neoforge.items.ItemHandlerCopySlot extends net.neoforged.neoforge.items.StackCopySlot {
  public net.neoforged.neoforge.items.ItemHandlerCopySlot(net.neoforged.neoforge.items.IItemHandler, int, int, int);
  public net.neoforged.neoforge.items.ItemHandlerCopySlot(net.neoforged.neoforge.items.SlotItemHandler);
  public boolean mayPlace(net.minecraft.world.item.ItemStack);
  public void onQuickCraft(net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack);
  public int getMaxStackSize();
  public int getMaxStackSize(net.minecraft.world.item.ItemStack);
  public boolean mayPickup(net.minecraft.world.entity.player.Player);
  public boolean isSameInventory(net.minecraft.world.inventory.Slot);
  public net.neoforged.neoforge.items.IItemHandler getItemHandler();
}
