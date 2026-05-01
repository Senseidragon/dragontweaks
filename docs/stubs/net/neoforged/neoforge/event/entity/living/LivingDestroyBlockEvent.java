Compiled from "LivingDestroyBlockEvent.java"
public class net.neoforged.neoforge.event.entity.living.LivingDestroyBlockEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.LivingDestroyBlockEvent(net.minecraft.world.entity.LivingEntity, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public net.minecraft.world.level.block.state.BlockState getState();
  public net.minecraft.core.BlockPos getPos();
}
