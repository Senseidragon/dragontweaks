Compiled from "SidedInvWrapper.java"
public class net.neoforged.neoforge.items.wrapper.SidedInvWrapper implements net.neoforged.neoforge.items.IItemHandlerModifiable {
  public net.neoforged.neoforge.items.wrapper.SidedInvWrapper(net.minecraft.world.WorldlyContainer, net.minecraft.core.Direction);
  public static int getSlot(net.minecraft.world.WorldlyContainer, int, net.minecraft.core.Direction);
  public boolean equals(java.lang.Object);
  public int hashCode();
  public int getSlots();
  public net.minecraft.world.item.ItemStack getStackInSlot(int);
  public net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public void setStackInSlot(int, net.minecraft.world.item.ItemStack);
  public net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public int getSlotLimit(int);
  public boolean isItemValid(int, net.minecraft.world.item.ItemStack);
}
