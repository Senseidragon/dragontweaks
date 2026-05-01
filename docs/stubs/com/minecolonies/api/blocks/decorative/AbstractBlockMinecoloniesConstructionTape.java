Compiled from "AbstractBlockMinecoloniesConstructionTape.java"
public abstract class com.minecolonies.api.blocks.decorative.AbstractBlockMinecoloniesConstructionTape<B extends com.minecolonies.api.blocks.decorative.AbstractBlockMinecoloniesConstructionTape<B>> extends com.minecolonies.api.blocks.AbstractBlockMinecoloniesFalling<B> implements net.minecraft.world.level.block.SimpleWaterloggedBlock {
  public static final net.minecraft.world.level.block.state.properties.BooleanProperty NORTH;
  public static final net.minecraft.world.level.block.state.properties.BooleanProperty EAST;
  public static final net.minecraft.world.level.block.state.properties.BooleanProperty SOUTH;
  public static final net.minecraft.world.level.block.state.properties.BooleanProperty WEST;
  public static final net.minecraft.world.level.block.state.properties.BooleanProperty WATERLOGGED;
  public static final net.minecraft.world.level.block.state.properties.DirectionProperty FACING;
  public static final net.minecraft.world.level.block.state.properties.BooleanProperty CORNER;
  public com.minecolonies.api.blocks.decorative.AbstractBlockMinecoloniesConstructionTape(net.minecraft.world.level.block.state.BlockBehaviour$Properties);
  public net.minecraft.world.phys.shapes.VoxelShape getShape(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos, net.minecraft.world.phys.shapes.CollisionContext);
  public net.minecraft.world.level.material.FluidState getFluidState(net.minecraft.world.level.block.state.BlockState);
}
