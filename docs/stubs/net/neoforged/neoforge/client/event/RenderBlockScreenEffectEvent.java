Compiled from "RenderBlockScreenEffectEvent.java"
public class net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent(net.minecraft.world.entity.player.Player, com.mojang.blaze3d.vertex.PoseStack, net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent$OverlayType, net.minecraft.world.level.block.state.BlockState, net.minecraft.core.BlockPos);
  public net.minecraft.world.entity.player.Player getPlayer();
  public com.mojang.blaze3d.vertex.PoseStack getPoseStack();
  public net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent$OverlayType getOverlayType();
  public net.minecraft.world.level.block.state.BlockState getBlockState();
  public net.minecraft.core.BlockPos getBlockPos();
}
