Compiled from "IMinecraftExtension.java"
public interface net.neoforged.neoforge.client.extensions.IMinecraftExtension {
  public default void pushGuiLayer(net.minecraft.client.gui.screens.Screen);
  public default void popGuiLayer();
  public default java.util.Locale getLocale();
}
