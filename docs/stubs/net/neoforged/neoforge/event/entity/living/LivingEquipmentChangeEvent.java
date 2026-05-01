Compiled from "LivingEquipmentChangeEvent.java"
public class net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent {
  public net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent(net.minecraft.world.entity.LivingEntity, net.minecraft.world.entity.EquipmentSlot, net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack);
  public net.minecraft.world.entity.EquipmentSlot getSlot();
  public net.minecraft.world.item.ItemStack getFrom();
  public net.minecraft.world.item.ItemStack getTo();
}
