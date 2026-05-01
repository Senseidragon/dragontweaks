Compiled from "ITickable.java"
public interface com.minecolonies.api.tileentities.ITickable {
  public default void tick(net.minecraft.world.level.Level, net.minecraft.world.level.block.state.BlockState, net.minecraft.core.BlockPos);
  public default void tick();
}
