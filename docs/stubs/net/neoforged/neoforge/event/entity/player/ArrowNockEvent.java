Compiled from "ArrowNockEvent.java"
public class net.neoforged.neoforge.event.entity.player.ArrowNockEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.player.ArrowNockEvent(net.minecraft.world.entity.player.Player, net.minecraft.world.item.ItemStack, net.minecraft.world.InteractionHand, net.minecraft.world.level.Level, boolean);
  public net.minecraft.world.item.ItemStack getBow();
  public net.minecraft.world.level.Level getLevel();
  public net.minecraft.world.InteractionHand getHand();
  public boolean hasAmmo();
  public net.minecraft.world.InteractionResultHolder<net.minecraft.world.item.ItemStack> getAction();
  public void setAction(net.minecraft.world.InteractionResultHolder<net.minecraft.world.item.ItemStack>);
}
