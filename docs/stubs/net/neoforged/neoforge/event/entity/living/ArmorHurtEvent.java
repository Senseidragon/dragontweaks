Compiled from "ArmorHurtEvent.java"
public class net.neoforged.neoforge.event.entity.living.ArmorHurtEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.ArmorHurtEvent(java.util.EnumMap<net.minecraft.world.entity.EquipmentSlot, net.neoforged.neoforge.event.entity.living.ArmorHurtEvent$ArmorEntry>, net.minecraft.world.entity.LivingEntity, net.minecraft.world.damagesource.DamageSource);
  public net.minecraft.world.item.ItemStack getArmorItemStack(net.minecraft.world.entity.EquipmentSlot);
  public java.lang.Float getOriginalDamage(net.minecraft.world.entity.EquipmentSlot);
  public java.lang.Float getNewDamage(net.minecraft.world.entity.EquipmentSlot);
  public void setNewDamage(net.minecraft.world.entity.EquipmentSlot, float);
  public java.util.Map<net.minecraft.world.entity.EquipmentSlot, net.neoforged.neoforge.event.entity.living.ArmorHurtEvent$ArmorEntry> getArmorMap();
  public net.minecraft.world.damagesource.DamageSource getDamageSource();
}
