Compiled from "RenderHandEvent.java"
public class net.neoforged.neoforge.client.event.RenderHandEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.client.event.RenderHandEvent(net.minecraft.world.InteractionHand, com.mojang.blaze3d.vertex.PoseStack, net.minecraft.client.renderer.MultiBufferSource, int, float, float, float, float, net.minecraft.world.item.ItemStack);
  public net.minecraft.world.InteractionHand getHand();
  public com.mojang.blaze3d.vertex.PoseStack getPoseStack();
  public net.minecraft.client.renderer.MultiBufferSource getMultiBufferSource();
  public int getPackedLight();
  public float getPartialTick();
  public float getInterpolatedPitch();
  public float getSwingProgress();
  public float getEquipProgress();
  public net.minecraft.world.item.ItemStack getItemStack();
}
