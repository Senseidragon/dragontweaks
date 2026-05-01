Compiled from "ITickableBlockMinecolonies.java"
public interface com.minecolonies.api.blocks.interfaces.ITickableBlockMinecolonies extends net.minecraft.world.level.block.EntityBlock {
  public default <T extends net.minecraft.world.level.block.entity.BlockEntity> net.minecraft.world.level.block.entity.BlockEntityTicker<T> getTicker(net.minecraft.world.level.Level, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.block.entity.BlockEntityType<T>);
  public static <E extends net.minecraft.world.level.block.entity.BlockEntity, A extends net.minecraft.world.level.block.entity.BlockEntity> net.minecraft.world.level.block.entity.BlockEntityTicker<A> createTickerHelper(net.minecraft.world.level.block.entity.BlockEntityType<A>, net.minecraft.world.level.block.entity.BlockEntityType<E>, net.minecraft.world.level.block.entity.BlockEntityTicker<? super E>);
}
