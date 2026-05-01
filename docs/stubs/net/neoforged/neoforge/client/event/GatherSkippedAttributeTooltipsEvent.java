Compiled from "GatherSkippedAttributeTooltipsEvent.java"
public class net.neoforged.neoforge.client.event.GatherSkippedAttributeTooltipsEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.client.event.GatherSkippedAttributeTooltipsEvent(net.minecraft.world.item.ItemStack, net.neoforged.neoforge.common.util.AttributeTooltipContext);
  public net.neoforged.neoforge.common.util.AttributeTooltipContext getContext();
  public net.minecraft.world.item.ItemStack getStack();
  public void skipId(net.minecraft.resources.ResourceLocation);
  public void skipGroup(net.minecraft.world.entity.EquipmentSlotGroup);
  public boolean isSkipped(net.minecraft.resources.ResourceLocation);
  public boolean isSkipped(net.minecraft.world.entity.EquipmentSlotGroup);
  public void setSkipAll(boolean);
  public boolean isSkippingAll();
}
