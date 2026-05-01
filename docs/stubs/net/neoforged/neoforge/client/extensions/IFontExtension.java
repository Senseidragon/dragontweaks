Compiled from "IFontExtension.java"
public interface net.neoforged.neoforge.client.extensions.IFontExtension {
  public static final net.minecraft.network.chat.FormattedText ELLIPSIS;
  public abstract net.minecraft.client.gui.Font self();
  public default net.minecraft.network.chat.FormattedText ellipsize(net.minecraft.network.chat.FormattedText, int);
}
