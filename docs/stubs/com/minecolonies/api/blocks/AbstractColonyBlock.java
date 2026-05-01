Compiled from "AbstractColonyBlock.java"
public abstract class com.minecolonies.api.blocks.AbstractColonyBlock<B extends com.minecolonies.api.blocks.AbstractColonyBlock<B>> extends com.minecolonies.api.blocks.AbstractBlockMinecolonies<B> implements com.minecolonies.api.entity.ai.workers.util.IBuilderUndestroyable, com.minecolonies.api.blocks.interfaces.ITickableBlockMinecolonies {
  public static final net.minecraft.world.level.block.state.properties.DirectionProperty FACING;
  public static final float HARDNESS;
  public static final float RESISTANCE;
  public com.minecolonies.api.blocks.AbstractColonyBlock();
  public float getDestroyProgress(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.entity.player.Player, net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos);
  public com.minecolonies.api.blocks.AbstractColonyBlock(net.minecraft.world.level.block.state.BlockBehaviour$Properties);
  public abstract java.lang.String getHutName();
  public net.minecraft.world.level.block.entity.BlockEntity newBlockEntity(net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public void onRemove(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, boolean);
  public abstract com.minecolonies.api.colony.buildings.registry.BuildingEntry getBuildingEntry();
  public net.minecraft.world.phys.shapes.VoxelShape getShape(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos, net.minecraft.world.phys.shapes.CollisionContext);
  public net.minecraft.world.ItemInteractionResult useItemOn(net.minecraft.world.item.ItemStack, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.entity.player.Player, net.minecraft.world.InteractionHand, net.minecraft.world.phys.BlockHitResult);
  public net.minecraft.world.level.block.state.BlockState getStateForPlacement(net.minecraft.world.item.context.BlockPlaceContext);
  public net.minecraft.world.level.block.state.BlockState rotate(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.block.Rotation);
  public void setPlacedBy(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.entity.LivingEntity, net.minecraft.world.item.ItemStack);
  public net.minecraft.resources.ResourceLocation getRegistryName();
  public B registerBlock(net.minecraft.core.Registry<net.minecraft.world.level.block.Block>);
  public com.minecolonies.api.blocks.AbstractBlockMinecolonies registerBlock(net.minecraft.core.Registry);
  public com.minecolonies.api.blocks.interfaces.IBlockMinecolonies registerBlock(net.minecraft.core.Registry);
}
