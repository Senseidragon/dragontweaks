Compiled from "PlayerInteractEvent.java"
public abstract class net.neoforged.neoforge.event.entity.player.PlayerInteractEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent {
  public net.minecraft.world.InteractionHand getHand();
  public net.minecraft.world.item.ItemStack getItemStack();
  public net.minecraft.core.BlockPos getPos();
  public net.minecraft.core.Direction getFace();
  public net.minecraft.world.level.Level getLevel();
  public net.neoforged.fml.LogicalSide getSide();
}
