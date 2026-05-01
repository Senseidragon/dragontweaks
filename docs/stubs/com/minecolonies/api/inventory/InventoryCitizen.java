Compiled from "InventoryCitizen.java"
public class com.minecolonies.api.inventory.InventoryCitizen implements net.neoforged.neoforge.items.IItemHandlerModifiable,net.minecraft.world.Nameable {
  public com.minecolonies.api.inventory.InventoryCitizen(java.lang.String, boolean, com.minecolonies.api.colony.ICitizenData);
  public com.minecolonies.api.inventory.InventoryCitizen(java.lang.String, boolean);
  public void setCustomName(java.lang.String);
  public net.minecraft.world.item.ItemStack getHeldItem(net.minecraft.world.InteractionHand);
  public void setHeldItem(net.minecraft.world.InteractionHand, int);
  public int getHeldItemSlot(net.minecraft.world.InteractionHand);
  public int getSlots();
  public boolean hasSpace();
  public boolean isEmpty();
  public boolean isFull();
  public net.minecraft.network.chat.Component getName();
  public boolean hasCustomName();
  public net.minecraft.world.item.ItemStack getStackInSlot(int);
  public net.minecraft.world.item.ItemStack getArmorInSlot(net.minecraft.world.entity.EquipmentSlot);
  public void forceArmorStackToSlot(net.minecraft.world.entity.EquipmentSlot, net.minecraft.world.item.ItemStack);
  public void forceClearArmorInSlot(net.minecraft.world.entity.EquipmentSlot, net.minecraft.world.item.ItemStack);
  public void transferArmorToSlot(net.minecraft.world.entity.EquipmentSlot, int);
  public void moveArmorToInventory(net.minecraft.world.entity.EquipmentSlot);
  public <T extends net.minecraft.world.entity.LivingEntity> boolean damageInventoryItem(int, int, T, java.util.function.Consumer<net.minecraft.world.item.Item>);
  public boolean shrinkInventoryItem(int);
  public net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public int getSlotLimit(int);
  public boolean isItemValid(int, net.minecraft.world.item.ItemStack);
  public void markDirty();
  public net.minecraft.network.chat.Component getDisplayName();
  public void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public void setStackInSlot(int, net.minecraft.world.item.ItemStack);
  public java.lang.Iterable<net.minecraft.world.item.ItemStack> getIterableArmorAndHandInv();
}
