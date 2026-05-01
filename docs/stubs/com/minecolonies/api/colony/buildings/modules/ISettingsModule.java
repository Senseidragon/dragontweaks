Compiled from "ISettingsModule.java"
public interface com.minecolonies.api.colony.buildings.modules.ISettingsModule extends com.minecolonies.api.colony.buildings.modules.IBuildingModule,com.minecolonies.api.colony.buildings.modules.ICommonSettingsModule {
  public abstract com.minecolonies.api.colony.buildings.modules.ISettingsModule with(com.minecolonies.api.colony.buildings.modules.settings.ISettingKey<?>, com.minecolonies.api.colony.buildings.modules.settings.ISetting<?>);
  public abstract <T extends com.minecolonies.api.colony.buildings.modules.settings.ISetting<?>> java.util.Optional<T> getOptionalSetting(com.minecolonies.api.colony.buildings.modules.settings.ISettingKey<T>);
  public abstract <S, T extends com.minecolonies.api.colony.buildings.modules.settings.ISetting<S>> S getSettingValueOrDefault(com.minecolonies.api.colony.buildings.modules.settings.ISettingKey<T>, S);
}
