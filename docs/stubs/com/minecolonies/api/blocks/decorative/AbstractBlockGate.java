Compiled from "AbstractBlockGate.java"
public abstract class com.minecolonies.api.blocks.decorative.AbstractBlockGate extends net.minecraft.world.level.block.DoorBlock implements net.minecraft.world.level.block.LiquidBlockContainer {
  public static final java.lang.String IRON_GATE;
  public static final java.lang.String WOODEN_GATE;
  public com.minecolonies.api.blocks.decorative.AbstractBlockGate(java.lang.String, float, int, int);
  public int getMaxWidth();
  public int getMaxHeight();
  public net.minecraft.world.level.block.state.BlockState playerWillDestroy(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.entity.player.Player);
  public int removeGate(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.core.Direction);
  public float getBlockHardness(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos);
  public net.minecraft.world.level.block.state.BlockState updateShape(net.minecraft.world.level.block.state.BlockState, net.minecraft.core.Direction, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos, net.minecraft.core.BlockPos);
  public boolean canSurvive(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.LevelReader, net.minecraft.core.BlockPos);
  public void setPlacedBy(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.entity.LivingEntity, net.minecraft.world.item.ItemStack);
  public net.minecraft.world.phys.shapes.VoxelShape getShape(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos, net.minecraft.world.phys.shapes.CollisionContext);
  public net.minecraft.world.phys.shapes.VoxelShape getCollisionShape(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos, net.minecraft.world.phys.shapes.CollisionContext);
  public net.minecraft.world.phys.shapes.VoxelShape getOcclusionShape(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos);
  public net.minecraft.world.level.block.RenderShape getRenderShape(net.minecraft.world.level.block.state.BlockState);
  public void setOpen(net.minecraft.world.entity.Entity, net.minecraft.world.level.Level, net.minecraft.world.level.block.state.BlockState, net.minecraft.core.BlockPos, boolean);
  public void toggleGate(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.core.Direction);
  public void neighborChanged(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.level.block.Block, net.minecraft.core.BlockPos, boolean);
  public net.minecraft.world.level.block.state.BlockState getStateForPlacement(net.minecraft.world.item.context.BlockPlaceContext);
  public com.minecolonies.api.blocks.decorative.AbstractBlockGate registerBlock(net.minecraft.core.Registry<net.minecraft.world.level.block.Block>);
  public void registerBlockItem(net.minecraft.core.Registry<net.minecraft.world.item.Item>, net.minecraft.world.item.Item$Properties);
  public boolean canPlaceLiquid(net.minecraft.world.entity.player.Player, net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.material.Fluid);
  public boolean placeLiquid(net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.material.FluidState);
}
