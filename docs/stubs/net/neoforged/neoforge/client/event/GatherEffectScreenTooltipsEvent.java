Compiled from "GatherEffectScreenTooltipsEvent.java"
public class net.neoforged.neoforge.client.event.GatherEffectScreenTooltipsEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.client.event.GatherEffectScreenTooltipsEvent(net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen<?>, net.minecraft.world.effect.MobEffectInstance, java.util.List<net.minecraft.network.chat.Component>);
  public net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen<?> getScreen();
  public net.minecraft.world.effect.MobEffectInstance getEffectInstance();
  public java.util.List<net.minecraft.network.chat.Component> getTooltip();
}
