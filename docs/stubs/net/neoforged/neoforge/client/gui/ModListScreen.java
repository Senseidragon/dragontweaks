Compiled from "ModListScreen.java"
public class net.neoforged.neoforge.client.gui.ModListScreen extends net.minecraft.client.gui.screens.Screen {
  public net.neoforged.neoforge.client.gui.ModListScreen(net.minecraft.client.gui.screens.Screen);
  public void init();
  public void tick();
  public <T extends net.minecraft.client.gui.components.ObjectSelectionList$Entry<T>> void buildModList(java.util.function.Consumer<T>, java.util.function.Function<net.neoforged.fml.ModContainer, T>);
  public void render(net.minecraft.client.gui.GuiGraphics, int, int, float);
  public net.minecraft.client.Minecraft getMinecraftInstance();
  public net.minecraft.client.gui.Font getFontRenderer();
  public void setSelected(net.neoforged.neoforge.client.gui.widget.ModListWidget$ModEntry);
  public void resize(net.minecraft.client.Minecraft, int, int);
  public void onClose();
}
