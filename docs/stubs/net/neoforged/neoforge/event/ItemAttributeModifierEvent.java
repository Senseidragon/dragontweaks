Compiled from "ItemAttributeModifierEvent.java"
public class net.neoforged.neoforge.event.ItemAttributeModifierEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.ItemAttributeModifierEvent(net.minecraft.world.item.ItemStack, net.minecraft.world.item.component.ItemAttributeModifiers);
  public net.minecraft.world.item.ItemStack getItemStack();
  public net.minecraft.world.item.component.ItemAttributeModifiers getDefaultModifiers();
  public java.util.List<net.minecraft.world.item.component.ItemAttributeModifiers$Entry> getModifiers();
  public boolean addModifier(net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute>, net.minecraft.world.entity.ai.attributes.AttributeModifier, net.minecraft.world.entity.EquipmentSlotGroup);
  public boolean removeModifier(net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute>, net.minecraft.resources.ResourceLocation);
  public void replaceModifier(net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute>, net.minecraft.world.entity.ai.attributes.AttributeModifier, net.minecraft.world.entity.EquipmentSlotGroup);
  public boolean removeIf(java.util.function.Predicate<net.minecraft.world.item.component.ItemAttributeModifiers$Entry>);
  public boolean removeAllModifiersFor(net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute>);
  public void clearModifiers();
  public net.minecraft.world.item.component.ItemAttributeModifiers build();
}
