Compiled from "InvWrapper.java"
public class net.neoforged.neoforge.items.wrapper.InvWrapper implements net.neoforged.neoforge.items.IItemHandlerModifiable {
  public net.neoforged.neoforge.items.wrapper.InvWrapper(net.minecraft.world.Container);
  public boolean equals(java.lang.Object);
  public int hashCode();
  public int getSlots();
  public net.minecraft.world.item.ItemStack getStackInSlot(int);
  public net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public void setStackInSlot(int, net.minecraft.world.item.ItemStack);
  public int getSlotLimit(int);
  public boolean isItemValid(int, net.minecraft.world.item.ItemStack);
  public net.minecraft.world.Container getInv();
}
