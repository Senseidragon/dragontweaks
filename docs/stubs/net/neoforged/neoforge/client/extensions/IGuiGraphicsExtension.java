Compiled from "IGuiGraphicsExtension.java"
public interface net.neoforged.neoforge.client.extensions.IGuiGraphicsExtension {
  public static final int DEFAULT_BACKGROUND_COLOR;
  public static final int DEFAULT_BORDER_COLOR_START;
  public static final int DEFAULT_BORDER_COLOR_END;
  public static final java.lang.String UNDO_CHAR;
  public static final java.lang.String RESET_CHAR;
  public static final java.lang.String VALID;
  public static final java.lang.String INVALID;
  public static final int[] TEXT_COLOR_CODES;
  public default int getColorFromFormattingCharacter(char, boolean);
  public default int drawScrollingString(net.minecraft.client.gui.Font, net.minecraft.network.chat.Component, int, int, int, int);
  public default void blitWithBorder(net.minecraft.resources.ResourceLocation, int, int, int, int, int, int, int, int, int);
  public default void blitWithBorder(net.minecraft.resources.ResourceLocation, int, int, int, int, int, int, int, int, int, int, int, int);
  public default void blitInscribed(net.minecraft.resources.ResourceLocation, int, int, int, int, int, int);
  public default void blitInscribed(net.minecraft.resources.ResourceLocation, int, int, int, int, int, int, boolean, boolean);
}
