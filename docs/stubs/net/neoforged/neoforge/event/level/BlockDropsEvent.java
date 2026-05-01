Compiled from "BlockDropsEvent.java"
public class net.neoforged.neoforge.event.level.BlockDropsEvent extends net.neoforged.neoforge.event.level.BlockEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.level.BlockDropsEvent(net.minecraft.server.level.ServerLevel, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.block.entity.BlockEntity, java.util.List<net.minecraft.world.entity.item.ItemEntity>, net.minecraft.world.entity.Entity, net.minecraft.world.item.ItemStack);
  public java.util.List<net.minecraft.world.entity.item.ItemEntity> getDrops();
  public net.minecraft.world.level.block.entity.BlockEntity getBlockEntity();
  public net.minecraft.world.entity.Entity getBreaker();
  public net.minecraft.world.item.ItemStack getTool();
  public void setCanceled(boolean);
  public net.minecraft.server.level.ServerLevel getLevel();
  public int getDroppedExperience();
  public void setDroppedExperience(int);
  public net.minecraft.world.level.LevelAccessor getLevel();
}
