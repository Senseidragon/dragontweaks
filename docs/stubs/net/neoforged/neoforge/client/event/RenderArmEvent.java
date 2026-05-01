Compiled from "RenderArmEvent.java"
public class net.neoforged.neoforge.client.event.RenderArmEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.client.event.RenderArmEvent(com.mojang.blaze3d.vertex.PoseStack, net.minecraft.client.renderer.MultiBufferSource, int, net.minecraft.client.player.AbstractClientPlayer, net.minecraft.world.entity.HumanoidArm);
  public net.minecraft.world.entity.HumanoidArm getArm();
  public com.mojang.blaze3d.vertex.PoseStack getPoseStack();
  public net.minecraft.client.renderer.MultiBufferSource getMultiBufferSource();
  public int getPackedLight();
  public net.minecraft.client.player.AbstractClientPlayer getPlayer();
}
