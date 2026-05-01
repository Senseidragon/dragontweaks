Compiled from "ForwardingItemHandler.java"
public class net.neoforged.neoforge.items.wrapper.ForwardingItemHandler implements net.neoforged.neoforge.items.IItemHandler {
  public net.neoforged.neoforge.items.wrapper.ForwardingItemHandler(net.neoforged.neoforge.items.IItemHandler);
  public net.neoforged.neoforge.items.wrapper.ForwardingItemHandler(java.util.function.Supplier<net.neoforged.neoforge.items.IItemHandler>);
  public int getSlots();
  public net.minecraft.world.item.ItemStack getStackInSlot(int);
  public net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public int getSlotLimit(int);
  public boolean isItemValid(int, net.minecraft.world.item.ItemStack);
}
