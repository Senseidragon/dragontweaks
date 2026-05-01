Compiled from "IItemHandler.java"
public interface net.neoforged.neoforge.items.IItemHandler {
  public abstract int getSlots();
  public abstract net.minecraft.world.item.ItemStack getStackInSlot(int);
  public abstract net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public abstract net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public abstract int getSlotLimit(int);
  public abstract boolean isItemValid(int, net.minecraft.world.item.ItemStack);
}
