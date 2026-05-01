Compiled from "ConfigurationScreen.java"
public final class net.neoforged.neoforge.client.gui.ConfigurationScreen extends net.minecraft.client.gui.screens.options.OptionsSubScreen {
  public static final net.minecraft.network.chat.Component CRUMB_SEPARATOR;
  public static final net.minecraft.network.chat.Component TOOLTIP_CANNOT_EDIT_THIS_WHILE_ONLINE;
  public static final net.minecraft.network.chat.Component TOOLTIP_CANNOT_EDIT_THIS_WHILE_OPEN_TO_LAN;
  public static final net.minecraft.network.chat.Component TOOLTIP_CANNOT_EDIT_NOT_LOADED;
  public static final net.minecraft.network.chat.Component NEW_LIST_ELEMENT;
  public static final net.minecraft.network.chat.Component MOVE_LIST_ELEMENT_UP;
  public static final net.minecraft.network.chat.Component MOVE_LIST_ELEMENT_DOWN;
  public static final net.minecraft.network.chat.Component REMOVE_LIST_ELEMENT;
  public static final net.minecraft.network.chat.Component UNSUPPORTED_ELEMENT;
  public static final net.minecraft.network.chat.Component LONG_STRING;
  public static final net.minecraft.network.chat.Component GAME_RESTART_TITLE;
  public static final net.minecraft.network.chat.Component GAME_RESTART_MESSAGE;
  public static final net.minecraft.network.chat.Component GAME_RESTART_YES;
  public static final net.minecraft.network.chat.Component SERVER_RESTART_TITLE;
  public static final net.minecraft.network.chat.Component SERVER_RESTART_MESSAGE;
  public static final net.minecraft.network.chat.Component RETURN_TO_MENU;
  public static final net.minecraft.network.chat.Component SAVING_LEVEL;
  public static final net.minecraft.network.chat.Component RESTART_NO;
  public static final net.minecraft.network.chat.Component RESTART_NO_TOOLTIP;
  public static final net.minecraft.network.chat.Component UNDO;
  public static final net.minecraft.network.chat.Component UNDO_TOOLTIP;
  public static final net.minecraft.network.chat.Component RESET;
  public static final net.minecraft.network.chat.Component RESET_TOOLTIP;
  public static final int BIG_BUTTON_WIDTH;
  public net.neoforged.neoforge.common.ModConfigSpec$RestartType needsRestart;
  public net.neoforged.neoforge.client.gui.ConfigurationScreen(net.neoforged.fml.ModContainer, net.minecraft.client.gui.screens.Screen);
  public net.neoforged.neoforge.client.gui.ConfigurationScreen(net.neoforged.fml.ModContainer, net.minecraft.client.gui.screens.Screen, net.neoforged.neoforge.client.gui.ConfigurationScreen$ConfigurationSectionScreen$Filter);
  public net.neoforged.neoforge.client.gui.ConfigurationScreen(net.neoforged.fml.ModContainer, net.minecraft.client.gui.screens.Screen, net.minecraft.data.models.blockstates.PropertyDispatch$QuadFunction<net.neoforged.neoforge.client.gui.ConfigurationScreen, net.neoforged.fml.config.ModConfig$Type, net.neoforged.fml.config.ModConfig, net.minecraft.network.chat.Component, net.minecraft.client.gui.screens.Screen>);
  public net.minecraft.network.chat.Component translatableConfig(net.neoforged.fml.config.ModConfig, java.lang.String, java.lang.String);
  public void added();
  public void onClose();
}
