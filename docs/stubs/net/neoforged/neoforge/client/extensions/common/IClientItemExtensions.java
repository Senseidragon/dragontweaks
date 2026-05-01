Compiled from "IClientItemExtensions.java"
public interface net.neoforged.neoforge.client.extensions.common.IClientItemExtensions {
  public static final net.neoforged.neoforge.client.extensions.common.IClientItemExtensions DEFAULT;
  public static net.neoforged.neoforge.client.extensions.common.IClientItemExtensions of(net.minecraft.world.item.ItemStack);
  public static net.neoforged.neoforge.client.extensions.common.IClientItemExtensions of(net.minecraft.world.item.Item);
  public default net.minecraft.client.gui.Font getFont(net.minecraft.world.item.ItemStack, net.neoforged.neoforge.client.extensions.common.IClientItemExtensions$FontContext);
  public default net.minecraft.client.model.HumanoidModel$ArmPose getArmPose(net.minecraft.world.entity.LivingEntity, net.minecraft.world.InteractionHand, net.minecraft.world.item.ItemStack);
  public default boolean applyForgeHandTransform(com.mojang.blaze3d.vertex.PoseStack, net.minecraft.client.player.LocalPlayer, net.minecraft.world.entity.HumanoidArm, net.minecraft.world.item.ItemStack, float, float, float);
  public default net.minecraft.client.model.HumanoidModel<?> getHumanoidArmorModel(net.minecraft.world.entity.LivingEntity, net.minecraft.world.item.ItemStack, net.minecraft.world.entity.EquipmentSlot, net.minecraft.client.model.HumanoidModel<?>);
  public default net.minecraft.client.model.Model getGenericArmorModel(net.minecraft.world.entity.LivingEntity, net.minecraft.world.item.ItemStack, net.minecraft.world.entity.EquipmentSlot, net.minecraft.client.model.HumanoidModel<?>);
  public default void setupModelAnimations(net.minecraft.world.entity.LivingEntity, net.minecraft.world.item.ItemStack, net.minecraft.world.entity.EquipmentSlot, net.minecraft.client.model.Model, float, float, float, float, float, float);
  public default void renderHelmetOverlay(net.minecraft.world.item.ItemStack, net.minecraft.world.entity.player.Player, int, int, float);
  public default void renderHelmetOverlay(net.minecraft.world.item.ItemStack, net.minecraft.world.entity.player.Player, net.minecraft.client.gui.GuiGraphics, net.minecraft.client.DeltaTracker);
  public default net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer getCustomRenderer();
  public default boolean shouldBobAsEntity(net.minecraft.world.item.ItemStack);
  public default boolean shouldSpreadAsEntity(net.minecraft.world.item.ItemStack);
  public default int getArmorLayerTintColor(net.minecraft.world.item.ItemStack, net.minecraft.world.entity.LivingEntity, net.minecraft.world.item.ArmorMaterial$Layer, int, int);
  public default int getDefaultDyeColor(net.minecraft.world.item.ItemStack);
  public default net.minecraft.resources.ResourceLocation getScopeOverlayTexture(net.minecraft.world.item.ItemStack);
}
