Compiled from "EntityAttributeModificationEvent.java"
public class net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent(java.util.Map<net.minecraft.world.entity.EntityType<? extends net.minecraft.world.entity.LivingEntity>, net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder>);
  public void add(net.minecraft.world.entity.EntityType<? extends net.minecraft.world.entity.LivingEntity>, net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute>, double);
  public void add(net.minecraft.world.entity.EntityType<? extends net.minecraft.world.entity.LivingEntity>, net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute>);
  public boolean has(net.minecraft.world.entity.EntityType<? extends net.minecraft.world.entity.LivingEntity>, net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute>);
  public java.util.List<net.minecraft.world.entity.EntityType<? extends net.minecraft.world.entity.LivingEntity>> getTypes();
}
