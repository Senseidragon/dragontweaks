Compiled from "AddAttributeTooltipsEvent.java"
public class net.neoforged.neoforge.client.event.AddAttributeTooltipsEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.client.event.AddAttributeTooltipsEvent(net.minecraft.world.item.ItemStack, java.util.function.Consumer<net.minecraft.network.chat.Component>, net.neoforged.neoforge.common.util.AttributeTooltipContext);
  public net.neoforged.neoforge.common.util.AttributeTooltipContext getContext();
  public net.minecraft.world.item.ItemStack getStack();
  public void addTooltipLines(net.minecraft.network.chat.Component...);
  public boolean shouldShow();
}
