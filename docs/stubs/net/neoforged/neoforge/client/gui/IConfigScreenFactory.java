Compiled from "IConfigScreenFactory.java"
public interface net.neoforged.neoforge.client.gui.IConfigScreenFactory extends net.neoforged.fml.IExtensionPoint {
  public abstract net.minecraft.client.gui.screens.Screen createScreen(net.neoforged.fml.ModContainer, net.minecraft.client.gui.screens.Screen);
  public static java.util.Optional<net.neoforged.neoforge.client.gui.IConfigScreenFactory> getForMod(net.neoforged.neoforgespi.language.IModInfo);
}
