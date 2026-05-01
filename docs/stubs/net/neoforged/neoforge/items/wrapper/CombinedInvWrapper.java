Compiled from "CombinedInvWrapper.java"
public class net.neoforged.neoforge.items.wrapper.CombinedInvWrapper implements net.neoforged.neoforge.items.IItemHandlerModifiable {
  public net.neoforged.neoforge.items.wrapper.CombinedInvWrapper(net.neoforged.neoforge.items.IItemHandlerModifiable...);
  public void setStackInSlot(int, net.minecraft.world.item.ItemStack);
  public int getSlots();
  public net.minecraft.world.item.ItemStack getStackInSlot(int);
  public net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public int getSlotLimit(int);
  public boolean isItemValid(int, net.minecraft.world.item.ItemStack);
}
