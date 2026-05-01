Compiled from "UseItemOnBlockEvent.java"
public class net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent(net.minecraft.world.item.context.UseOnContext, net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent$UsePhase);
  public net.minecraft.world.entity.player.Player getPlayer();
  public net.minecraft.world.InteractionHand getHand();
  public net.minecraft.world.item.ItemStack getItemStack();
  public net.minecraft.core.BlockPos getPos();
  public net.minecraft.core.Direction getFace();
  public net.minecraft.world.level.Level getLevel();
  public net.minecraft.world.item.context.UseOnContext getUseOnContext();
  public net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent$UsePhase getUsePhase();
  public net.neoforged.fml.LogicalSide getSide();
  public void cancelWithResult(net.minecraft.world.ItemInteractionResult);
  public net.minecraft.world.ItemInteractionResult getCancellationResult();
  public void setCancellationResult(net.minecraft.world.ItemInteractionResult);
}
