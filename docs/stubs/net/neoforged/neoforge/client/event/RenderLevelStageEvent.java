Compiled from "RenderLevelStageEvent.java"
public class net.neoforged.neoforge.client.event.RenderLevelStageEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.client.event.RenderLevelStageEvent(net.neoforged.neoforge.client.event.RenderLevelStageEvent$Stage, net.minecraft.client.renderer.LevelRenderer, com.mojang.blaze3d.vertex.PoseStack, org.joml.Matrix4f, org.joml.Matrix4f, int, net.minecraft.client.DeltaTracker, net.minecraft.client.Camera, net.minecraft.client.renderer.culling.Frustum);
  public net.neoforged.neoforge.client.event.RenderLevelStageEvent$Stage getStage();
  public net.minecraft.client.renderer.LevelRenderer getLevelRenderer();
  public com.mojang.blaze3d.vertex.PoseStack getPoseStack();
  public org.joml.Matrix4f getModelViewMatrix();
  public org.joml.Matrix4f getProjectionMatrix();
  public int getRenderTick();
  public net.minecraft.client.DeltaTracker getPartialTick();
  public net.minecraft.client.Camera getCamera();
  public net.minecraft.client.renderer.culling.Frustum getFrustum();
}
