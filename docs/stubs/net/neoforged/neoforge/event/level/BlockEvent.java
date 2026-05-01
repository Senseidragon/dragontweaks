Compiled from "BlockEvent.java"
public abstract class net.neoforged.neoforge.event.level.BlockEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.level.BlockEvent(net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public net.minecraft.world.level.LevelAccessor getLevel();
  public net.minecraft.core.BlockPos getPos();
  public net.minecraft.world.level.block.state.BlockState getState();
}
