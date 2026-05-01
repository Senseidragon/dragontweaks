Compiled from "QuadLighter.java"
public abstract class net.neoforged.neoforge.client.model.lighting.QuadLighter {
  public final void setup(net.minecraft.world.level.BlockAndTintGetter, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public final void reset();
  public final void computeLightingForQuad(net.minecraft.client.renderer.block.model.BakedQuad);
  public final void computeLightingForQuad(int[], boolean);
  public final float[] getComputedBrightness();
  public final int[] getComputedLightmap();
  public final void process(com.mojang.blaze3d.vertex.VertexConsumer, com.mojang.blaze3d.vertex.PoseStack$Pose, net.minecraft.client.renderer.block.model.BakedQuad, int);
  public static float calculateShade(float, float, float, boolean);
}
