Compiled from "IKeyMappingExtension.java"
public interface net.neoforged.neoforge.client.extensions.IKeyMappingExtension {
  public abstract com.mojang.blaze3d.platform.InputConstants$Key getKey();
  public default boolean isActiveAndMatches(com.mojang.blaze3d.platform.InputConstants$Key);
  public default void setToDefault();
  public abstract void setKeyConflictContext(net.neoforged.neoforge.client.settings.IKeyConflictContext);
  public abstract net.neoforged.neoforge.client.settings.IKeyConflictContext getKeyConflictContext();
  public abstract net.neoforged.neoforge.client.settings.KeyModifier getDefaultKeyModifier();
  public abstract net.neoforged.neoforge.client.settings.KeyModifier getKeyModifier();
  public abstract void setKeyModifierAndCode(net.neoforged.neoforge.client.settings.KeyModifier, com.mojang.blaze3d.platform.InputConstants$Key);
  public default boolean isConflictContextAndModifierActive();
  public default boolean hasKeyModifierConflict(net.minecraft.client.KeyMapping);
  public default net.minecraft.network.chat.Component getDisplayName();
}
