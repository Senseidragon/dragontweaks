Compiled from "ISetting.java"
public interface com.minecolonies.api.colony.buildings.modules.settings.ISetting<S> {
  public abstract net.minecraft.resources.ResourceLocation getLayoutItem();
  public abstract void setupHandler(com.minecolonies.api.colony.buildings.modules.settings.ISettingKey<?>, com.ldtteam.blockui.Pane, com.minecolonies.api.colony.buildings.modules.ICommonSettingsModule, com.minecolonies.api.colony.buildings.views.IBuildingView, com.ldtteam.blockui.views.BOWindow);
  public abstract void render(com.minecolonies.api.colony.buildings.modules.settings.ISettingKey<?>, com.ldtteam.blockui.Pane, com.minecolonies.api.colony.buildings.modules.ICommonSettingsModule, com.minecolonies.api.colony.buildings.views.IBuildingView, com.ldtteam.blockui.views.BOWindow);
  public default void trigger();
  public default boolean isActive(com.minecolonies.api.colony.buildings.modules.ISettingsModule);
  public default boolean shouldHideWhenInactive();
  public default void onUpdate(com.minecolonies.api.colony.buildings.IBuilding, net.minecraft.server.level.ServerPlayer);
  public default void updateSetting(com.minecolonies.api.colony.buildings.modules.settings.ISetting<?>);
  public abstract void copyValue(com.minecolonies.api.colony.buildings.modules.settings.ISetting<?>);
  public default void setHoverPane(com.minecolonies.api.colony.buildings.modules.settings.ISettingKey<?>, com.ldtteam.blockui.Pane, com.minecolonies.api.colony.buildings.modules.ICommonSettingsModule);
  public default net.minecraft.network.chat.Component getInactiveReason();
  public default boolean isActive(com.minecolonies.api.colony.buildings.modules.settings.ISettingsModuleView);
  public abstract S getValue();
  public default net.minecraft.network.chat.Component getToolTipText();
}
