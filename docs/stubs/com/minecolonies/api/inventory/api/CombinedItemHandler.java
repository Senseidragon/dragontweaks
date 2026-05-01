Compiled from "CombinedItemHandler.java"
public class com.minecolonies.api.inventory.api.CombinedItemHandler implements net.neoforged.neoforge.items.IItemHandlerModifiable, net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag>, com.minecolonies.api.inventory.api.IWorldNameableModifiable {
  public com.minecolonies.api.inventory.api.CombinedItemHandler(java.lang.String, net.neoforged.neoforge.items.IItemHandlerModifiable...);
  public com.minecolonies.api.inventory.api.CombinedItemHandler(java.lang.String, java.lang.String, net.neoforged.neoforge.items.IItemHandlerModifiable...);
  public net.minecraft.nbt.CompoundTag serializeNBT(net.minecraft.core.HolderLookup$Provider);
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public void setStackInSlot(int, net.minecraft.world.item.ItemStack);
  public int getLastIndex(int);
  public int getSlots();
  public net.minecraft.world.item.ItemStack getStackInSlot(int);
  public net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public int getSlotLimit(int);
  public boolean isItemValid(int, net.minecraft.world.item.ItemStack);
  public void setName(java.lang.String);
  public net.minecraft.network.chat.Component getName();
  public boolean equals(java.lang.Object);
  public int hashCode();
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.Tag);
  public net.minecraft.nbt.Tag serializeNBT(net.minecraft.core.HolderLookup$Provider);
}
