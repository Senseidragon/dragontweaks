Compiled from "BonemealEvent.java"
public class net.neoforged.neoforge.event.entity.player.BonemealEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.player.BonemealEvent(net.minecraft.world.entity.player.Player, net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.item.ItemStack);
  public net.minecraft.world.entity.player.Player getPlayer();
  public net.minecraft.world.level.Level getLevel();
  public net.minecraft.core.BlockPos getPos();
  public net.minecraft.world.level.block.state.BlockState getState();
  public net.minecraft.world.item.ItemStack getStack();
  public boolean isValidBonemealTarget();
  public void setSuccessful(boolean);
  public boolean isSuccessful();
  public void setCanceled(boolean);
}
