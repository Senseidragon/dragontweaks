Compiled from "RangedWrapper.java"
public class net.neoforged.neoforge.items.wrapper.RangedWrapper implements net.neoforged.neoforge.items.IItemHandlerModifiable {
  public net.neoforged.neoforge.items.wrapper.RangedWrapper(net.neoforged.neoforge.items.IItemHandlerModifiable, int, int);
  public int getSlots();
  public net.minecraft.world.item.ItemStack getStackInSlot(int);
  public net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public void setStackInSlot(int, net.minecraft.world.item.ItemStack);
  public int getSlotLimit(int);
  public boolean isItemValid(int, net.minecraft.world.item.ItemStack);
}
