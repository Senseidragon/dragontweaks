Compiled from "IBlockCapabilityProvider.java"
public interface net.neoforged.neoforge.capabilities.IBlockCapabilityProvider<T, C> {
  public abstract T getCapability(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.block.entity.BlockEntity, C);
}
