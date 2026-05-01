Compiled from "ItemStackHandler.java"
public class net.neoforged.neoforge.items.ItemStackHandler implements net.neoforged.neoforge.items.IItemHandler, net.neoforged.neoforge.items.IItemHandlerModifiable, net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public net.neoforged.neoforge.items.ItemStackHandler();
  public net.neoforged.neoforge.items.ItemStackHandler(int);
  public net.neoforged.neoforge.items.ItemStackHandler(net.minecraft.core.NonNullList<net.minecraft.world.item.ItemStack>);
  public void setSize(int);
  public void setStackInSlot(int, net.minecraft.world.item.ItemStack);
  public int getSlots();
  public net.minecraft.world.item.ItemStack getStackInSlot(int);
  public net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public int getSlotLimit(int);
  public boolean isItemValid(int, net.minecraft.world.item.ItemStack);
  public net.minecraft.nbt.CompoundTag serializeNBT(net.minecraft.core.HolderLookup$Provider);
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.Tag);
  public net.minecraft.nbt.Tag serializeNBT(net.minecraft.core.HolderLookup$Provider);
}
