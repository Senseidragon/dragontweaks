Compiled from "RenderItemInFrameEvent.java"
public class net.neoforged.neoforge.client.event.RenderItemInFrameEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.client.event.RenderItemInFrameEvent(net.minecraft.world.entity.decoration.ItemFrame, net.minecraft.client.renderer.entity.ItemFrameRenderer<?>, com.mojang.blaze3d.vertex.PoseStack, net.minecraft.client.renderer.MultiBufferSource, int);
  public net.minecraft.world.item.ItemStack getItemStack();
  public net.minecraft.world.entity.decoration.ItemFrame getItemFrameEntity();
  public net.minecraft.client.renderer.entity.ItemFrameRenderer<?> getRenderer();
  public com.mojang.blaze3d.vertex.PoseStack getPoseStack();
  public net.minecraft.client.renderer.MultiBufferSource getMultiBufferSource();
  public int getPackedLight();
}
