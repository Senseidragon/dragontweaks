Compiled from "RenderHighlightEvent.java"
public abstract class net.neoforged.neoforge.client.event.RenderHighlightEvent extends net.neoforged.bus.api.Event {
  public net.minecraft.client.renderer.LevelRenderer getLevelRenderer();
  public net.minecraft.client.Camera getCamera();
  public net.minecraft.world.phys.HitResult getTarget();
  public net.minecraft.client.DeltaTracker getDeltaTracker();
  public com.mojang.blaze3d.vertex.PoseStack getPoseStack();
  public net.minecraft.client.renderer.MultiBufferSource getMultiBufferSource();
}
