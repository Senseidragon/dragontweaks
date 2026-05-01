Compiled from "CustomizeGuiOverlayEvent.java"
public abstract class net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent extends net.neoforged.bus.api.Event {
  public com.mojang.blaze3d.platform.Window getWindow();
  public net.minecraft.client.gui.GuiGraphics getGuiGraphics();
  public net.minecraft.client.DeltaTracker getPartialTick();
}
