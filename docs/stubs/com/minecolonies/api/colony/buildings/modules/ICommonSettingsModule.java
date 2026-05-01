Compiled from "ICommonSettingsModule.java"
public interface com.minecolonies.api.colony.buildings.modules.ICommonSettingsModule {
  public abstract <T extends com.minecolonies.api.colony.buildings.modules.settings.ISetting<?>> T getSetting(com.minecolonies.api.colony.buildings.modules.settings.ISettingKey<T>);
  public default void trigger(com.minecolonies.api.colony.buildings.modules.settings.ISettingKey<?>);
  public abstract void updateSetting(com.minecolonies.api.colony.buildings.modules.settings.ISettingKey<?>, com.minecolonies.api.colony.buildings.modules.settings.ISetting<?>, net.minecraft.server.level.ServerPlayer);
}
