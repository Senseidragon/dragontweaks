Compiled from "RenderNameTagEvent.java"
public class net.neoforged.neoforge.client.event.RenderNameTagEvent extends net.neoforged.neoforge.event.entity.EntityEvent {
  public net.neoforged.neoforge.client.event.RenderNameTagEvent(net.minecraft.world.entity.Entity, net.minecraft.network.chat.Component, net.minecraft.client.renderer.entity.EntityRenderer<?>, com.mojang.blaze3d.vertex.PoseStack, net.minecraft.client.renderer.MultiBufferSource, int, float);
  public void setCanRender(net.neoforged.neoforge.common.util.TriState);
  public net.neoforged.neoforge.common.util.TriState canRender();
  public void setContent(net.minecraft.network.chat.Component);
  public net.minecraft.network.chat.Component getContent();
  public net.minecraft.network.chat.Component getOriginalContent();
  public net.minecraft.client.renderer.entity.EntityRenderer<?> getEntityRenderer();
  public com.mojang.blaze3d.vertex.PoseStack getPoseStack();
  public net.minecraft.client.renderer.MultiBufferSource getMultiBufferSource();
  public int getPackedLight();
  public float getPartialTick();
}
