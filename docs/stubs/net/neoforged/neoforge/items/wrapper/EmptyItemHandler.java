Compiled from "EmptyItemHandler.java"
public class net.neoforged.neoforge.items.wrapper.EmptyItemHandler implements net.neoforged.neoforge.items.IItemHandlerModifiable {
  public static final net.neoforged.neoforge.items.IItemHandler INSTANCE;
  public net.neoforged.neoforge.items.wrapper.EmptyItemHandler();
  public int getSlots();
  public net.minecraft.world.item.ItemStack getStackInSlot(int);
  public net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public void setStackInSlot(int, net.minecraft.world.item.ItemStack);
  public int getSlotLimit(int);
  public boolean isItemValid(int, net.minecraft.world.item.ItemStack);
}
