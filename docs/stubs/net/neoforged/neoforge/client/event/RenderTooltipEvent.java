Compiled from "RenderTooltipEvent.java"
public abstract class net.neoforged.neoforge.client.event.RenderTooltipEvent extends net.neoforged.bus.api.Event {
  public net.minecraft.world.item.ItemStack getItemStack();
  public net.minecraft.client.gui.GuiGraphics getGraphics();
  public java.util.List<net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent> getComponents();
  public int getX();
  public int getY();
  public net.minecraft.client.gui.Font getFont();
}
