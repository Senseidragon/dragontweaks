Compiled from "EntityEquipmentInvWrapper.java"
public abstract class net.neoforged.neoforge.items.wrapper.EntityEquipmentInvWrapper implements net.neoforged.neoforge.items.IItemHandlerModifiable {
  public net.neoforged.neoforge.items.wrapper.EntityEquipmentInvWrapper(net.minecraft.world.entity.LivingEntity, net.minecraft.world.entity.EquipmentSlot$Type);
  public int getSlots();
  public net.minecraft.world.item.ItemStack getStackInSlot(int);
  public net.minecraft.world.item.ItemStack insertItem(int, net.minecraft.world.item.ItemStack, boolean);
  public net.minecraft.world.item.ItemStack extractItem(int, int, boolean);
  public int getSlotLimit(int);
  public void setStackInSlot(int, net.minecraft.world.item.ItemStack);
  public boolean isItemValid(int, net.minecraft.world.item.ItemStack);
}
