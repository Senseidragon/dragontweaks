Compiled from "RenderGuiLayerEvent.java"
public abstract class net.neoforged.neoforge.client.event.RenderGuiLayerEvent extends net.neoforged.bus.api.Event {
  public net.minecraft.client.gui.GuiGraphics getGuiGraphics();
  public net.minecraft.client.DeltaTracker getPartialTick();
  public net.minecraft.resources.ResourceLocation getName();
  public net.minecraft.client.gui.LayeredDraw$Layer getLayer();
}
