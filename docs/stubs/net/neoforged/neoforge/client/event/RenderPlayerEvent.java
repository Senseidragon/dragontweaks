Compiled from "RenderPlayerEvent.java"
public abstract class net.neoforged.neoforge.client.event.RenderPlayerEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent {
  public net.minecraft.client.renderer.entity.player.PlayerRenderer getRenderer();
  public float getPartialTick();
  public com.mojang.blaze3d.vertex.PoseStack getPoseStack();
  public net.minecraft.client.renderer.MultiBufferSource getMultiBufferSource();
  public int getPackedLight();
}
