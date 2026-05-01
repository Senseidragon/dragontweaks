Compiled from "ComponentItemHandler.java"
public class net.neoforged.neoforge.items.ComponentItemHandler implements net.neoforged.neoforge.items.IItemHandlerModifiable {
  public net.neoforged.neoforge.items.ComponentItemHandler(net.neoforged.neoforge.common.MutableDataComponentHolder, net.minecraft.core.component.DataComponentType<net.minecraft.world.item.component.ItemContainerContents>, int);
  public int getSlots();
  public net.minecraft.world.item.ItemStack getStackInSlot(int);
  public void setStackInSlot(int, net.minecraft.world.item.ItemStack);
  public net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public int getSlotLimit(int);
  public boolean isItemValid(int, net.minecraft.world.item.ItemStack);
}
