Compiled from "ScreenUtils.java"
public class net.neoforged.neoforge.client.gui.ScreenUtils {
  public static final int DEFAULT_BACKGROUND_COLOR;
  public static final int DEFAULT_BORDER_COLOR_START;
  public static final int DEFAULT_BORDER_COLOR_END;
  public static final java.lang.String UNDO_CHAR;
  public static final java.lang.String RESET_CHAR;
  public static final java.lang.String VALID;
  public static final java.lang.String INVALID;
  public static int[] TEXT_COLOR_CODES;
  public net.neoforged.neoforge.client.gui.ScreenUtils();
  public static int getColorFromFormattingCharacter(char, boolean);
  public static void blitWithBorder(net.minecraft.client.gui.GuiGraphics, int, int, int, int, int, int, int, int, int, float);
  public static void blitWithBorder(net.minecraft.client.gui.GuiGraphics, net.minecraft.resources.ResourceLocation, int, int, int, int, int, int, int, int, int, float);
  public static void blitWithBorder(net.minecraft.client.gui.GuiGraphics, net.minecraft.resources.ResourceLocation, int, int, int, int, int, int, int, int, int, int, int, int, float);
  public static void blitWithBorder(net.minecraft.client.gui.GuiGraphics, int, int, int, int, int, int, int, int, int, int, int, int, float);
  public static void drawTexturedModalRect(net.minecraft.client.gui.GuiGraphics, int, int, int, int, int, int, float);
  public static void drawGradientRect(org.joml.Matrix4f, int, int, int, int, int, int, int);
  public static void blitInscribed(net.minecraft.client.gui.GuiGraphics, net.minecraft.resources.ResourceLocation, int, int, int, int, int, int);
  public static void blitInscribed(net.minecraft.client.gui.GuiGraphics, net.minecraft.resources.ResourceLocation, int, int, int, int, int, int, boolean, boolean);
}
