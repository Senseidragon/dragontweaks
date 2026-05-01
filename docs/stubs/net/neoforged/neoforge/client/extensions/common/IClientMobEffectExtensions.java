Compiled from "IClientMobEffectExtensions.java"
public interface net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions {
  public static final net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions DEFAULT;
  public static net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions of(net.minecraft.world.effect.MobEffectInstance);
  public static net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions of(net.minecraft.world.effect.MobEffect);
  public default boolean isVisibleInInventory(net.minecraft.world.effect.MobEffectInstance);
  public default boolean isVisibleInGui(net.minecraft.world.effect.MobEffectInstance);
  public default boolean renderInventoryIcon(net.minecraft.world.effect.MobEffectInstance, net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen<?>, net.minecraft.client.gui.GuiGraphics, int, int, int);
  public default boolean renderInventoryText(net.minecraft.world.effect.MobEffectInstance, net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen<?>, net.minecraft.client.gui.GuiGraphics, int, int, int);
  public default boolean renderGuiIcon(net.minecraft.world.effect.MobEffectInstance, net.minecraft.client.gui.Gui, net.minecraft.client.gui.GuiGraphics, int, int, float, float);
}
