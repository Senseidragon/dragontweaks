Compiled from "GuiLayerManager.java"
public class net.neoforged.neoforge.client.gui.GuiLayerManager {
  public static final float Z_SEPARATION;
  public net.neoforged.neoforge.client.gui.GuiLayerManager();
  public net.neoforged.neoforge.client.gui.GuiLayerManager add(net.minecraft.resources.ResourceLocation, net.minecraft.client.gui.LayeredDraw$Layer);
  public net.neoforged.neoforge.client.gui.GuiLayerManager add(net.neoforged.neoforge.client.gui.GuiLayerManager, java.util.function.BooleanSupplier);
  public void render(net.minecraft.client.gui.GuiGraphics, net.minecraft.client.DeltaTracker);
  public void initModdedLayers();
  public int getLayerCount();
}
