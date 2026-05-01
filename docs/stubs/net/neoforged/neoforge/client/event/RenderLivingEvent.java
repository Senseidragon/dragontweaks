Compiled from "RenderLivingEvent.java"
public abstract class net.neoforged.neoforge.client.event.RenderLivingEvent<T extends net.minecraft.world.entity.LivingEntity, M extends net.minecraft.client.model.EntityModel<T>> extends net.neoforged.bus.api.Event {
  public net.minecraft.world.entity.LivingEntity getEntity();
  public net.minecraft.client.renderer.entity.LivingEntityRenderer<T, M> getRenderer();
  public float getPartialTick();
  public com.mojang.blaze3d.vertex.PoseStack getPoseStack();
  public net.minecraft.client.renderer.MultiBufferSource getMultiBufferSource();
  public int getPackedLight();
}
