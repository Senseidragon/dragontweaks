Compiled from "SlotItemHandler.java"
public class net.neoforged.neoforge.items.SlotItemHandler extends net.minecraft.world.inventory.Slot {
  public net.neoforged.neoforge.items.SlotItemHandler(net.neoforged.neoforge.items.IItemHandler, int, int, int);
  public boolean mayPlace(net.minecraft.world.item.ItemStack);
  public net.minecraft.world.item.ItemStack getItem();
  public void set(net.minecraft.world.item.ItemStack);
  public void initialize(net.minecraft.world.item.ItemStack);
  public void onQuickCraft(net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack);
  public int getMaxStackSize();
  public int getMaxStackSize(net.minecraft.world.item.ItemStack);
  public boolean mayPickup(net.minecraft.world.entity.player.Player);
  public net.minecraft.world.item.ItemStack remove(int);
  public net.neoforged.neoforge.items.IItemHandler getItemHandler();
}
