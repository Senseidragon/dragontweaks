Compiled from "IClientFluidTypeExtensions.java"
public interface net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions {
  public static final net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions DEFAULT;
  public static net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions of(net.minecraft.world.level.material.FluidState);
  public static net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions of(net.minecraft.world.level.material.Fluid);
  public static net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions of(net.neoforged.neoforge.fluids.FluidType);
  public default int getTintColor();
  public default net.minecraft.resources.ResourceLocation getStillTexture();
  public default net.minecraft.resources.ResourceLocation getFlowingTexture();
  public default net.minecraft.resources.ResourceLocation getOverlayTexture();
  public default net.minecraft.resources.ResourceLocation getRenderOverlayTexture(net.minecraft.client.Minecraft);
  public default void renderOverlay(net.minecraft.client.Minecraft, com.mojang.blaze3d.vertex.PoseStack);
  public default org.joml.Vector3f modifyFogColor(net.minecraft.client.Camera, float, net.minecraft.client.multiplayer.ClientLevel, int, float, org.joml.Vector3f);
  public default void modifyFogRender(net.minecraft.client.Camera, net.minecraft.client.renderer.FogRenderer$FogMode, float, float, float, float, com.mojang.blaze3d.shaders.FogShape);
  public default net.minecraft.resources.ResourceLocation getStillTexture(net.minecraft.world.level.material.FluidState, net.minecraft.world.level.BlockAndTintGetter, net.minecraft.core.BlockPos);
  public default net.minecraft.resources.ResourceLocation getFlowingTexture(net.minecraft.world.level.material.FluidState, net.minecraft.world.level.BlockAndTintGetter, net.minecraft.core.BlockPos);
  public default net.minecraft.resources.ResourceLocation getOverlayTexture(net.minecraft.world.level.material.FluidState, net.minecraft.world.level.BlockAndTintGetter, net.minecraft.core.BlockPos);
  public default int getTintColor(net.minecraft.world.level.material.FluidState, net.minecraft.world.level.BlockAndTintGetter, net.minecraft.core.BlockPos);
  public default int getTintColor(net.neoforged.neoforge.fluids.FluidStack);
  public default net.minecraft.resources.ResourceLocation getStillTexture(net.neoforged.neoforge.fluids.FluidStack);
  public default net.minecraft.resources.ResourceLocation getFlowingTexture(net.neoforged.neoforge.fluids.FluidStack);
  public default net.minecraft.resources.ResourceLocation getOverlayTexture(net.neoforged.neoforge.fluids.FluidStack);
  public default boolean renderFluid(net.minecraft.world.level.material.FluidState, net.minecraft.world.level.BlockAndTintGetter, net.minecraft.core.BlockPos, com.mojang.blaze3d.vertex.VertexConsumer, net.minecraft.world.level.block.state.BlockState);
}
