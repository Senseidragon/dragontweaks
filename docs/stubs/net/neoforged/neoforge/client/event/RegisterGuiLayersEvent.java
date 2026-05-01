Compiled from "RegisterGuiLayersEvent.java"
public class net.neoforged.neoforge.client.event.RegisterGuiLayersEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.client.event.RegisterGuiLayersEvent(java.util.List<net.neoforged.neoforge.client.gui.GuiLayerManager$NamedLayer>);
  public void registerBelowAll(net.minecraft.resources.ResourceLocation, net.minecraft.client.gui.LayeredDraw$Layer);
  public void registerBelow(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation, net.minecraft.client.gui.LayeredDraw$Layer);
  public void registerAbove(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation, net.minecraft.client.gui.LayeredDraw$Layer);
  public void registerAboveAll(net.minecraft.resources.ResourceLocation, net.minecraft.client.gui.LayeredDraw$Layer);
  public void replaceLayer(net.minecraft.resources.ResourceLocation, net.minecraft.client.gui.LayeredDraw$Layer);
  public void wrapLayer(net.minecraft.resources.ResourceLocation, java.util.function.UnaryOperator<net.minecraft.client.gui.LayeredDraw$Layer>);
}
