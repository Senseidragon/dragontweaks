Compiled from "KeyModifier.java"
public abstract class net.neoforged.neoforge.client.settings.KeyModifier extends java.lang.Enum<net.neoforged.neoforge.client.settings.KeyModifier> {
  public static final net.neoforged.neoforge.client.settings.KeyModifier CONTROL;
  public static final net.neoforged.neoforge.client.settings.KeyModifier SHIFT;
  public static final net.neoforged.neoforge.client.settings.KeyModifier ALT;
  public static final net.neoforged.neoforge.client.settings.KeyModifier NONE;
  public static final net.neoforged.neoforge.client.settings.KeyModifier[] MODIFIER_VALUES;
  public static net.neoforged.neoforge.client.settings.KeyModifier[] values();
  public static net.neoforged.neoforge.client.settings.KeyModifier valueOf(java.lang.String);
  public static net.neoforged.neoforge.client.settings.KeyModifier getActiveModifier();
  public static java.util.List<net.neoforged.neoforge.client.settings.KeyModifier> getActiveModifiers();
  public static net.neoforged.neoforge.client.settings.KeyModifier getKeyModifier(com.mojang.blaze3d.platform.InputConstants$Key);
  public static boolean isKeyCodeModifier(com.mojang.blaze3d.platform.InputConstants$Key);
  public static net.neoforged.neoforge.client.settings.KeyModifier valueFromString(java.lang.String);
  public abstract boolean matches(com.mojang.blaze3d.platform.InputConstants$Key);
  public abstract boolean isActive(net.neoforged.neoforge.client.settings.IKeyConflictContext);
  public abstract net.minecraft.network.chat.Component getCombinedName(com.mojang.blaze3d.platform.InputConstants$Key, java.util.function.Supplier<net.minecraft.network.chat.Component>);
  public com.mojang.blaze3d.platform.InputConstants$Key[] codes();
}
