Compiled from "RegisterClientTooltipComponentFactoriesEvent.java"
public class net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent(java.util.Map<java.lang.Class<? extends net.minecraft.world.inventory.tooltip.TooltipComponent>, java.util.function.Function<net.minecraft.world.inventory.tooltip.TooltipComponent, net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent>>);
  public <T extends net.minecraft.world.inventory.tooltip.TooltipComponent> void register(java.lang.Class<T>, java.util.function.Function<? super T, ? extends net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent>);
}
