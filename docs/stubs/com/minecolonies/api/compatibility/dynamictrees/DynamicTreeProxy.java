Compiled from "DynamicTreeProxy.java"
public class com.minecolonies.api.compatibility.dynamictrees.DynamicTreeProxy {
  public com.minecolonies.api.compatibility.dynamictrees.DynamicTreeProxy();
  public boolean isDynamicTreePresent();
  public boolean checkForDynamicTreeBlock(net.minecraft.world.level.block.Block);
  public boolean checkForDynamicLeavesBlock(net.minecraft.world.level.block.Block);
  public boolean checkForDynamicTrunkShellBlock(net.minecraft.world.level.block.Block);
  public net.minecraft.core.NonNullList<net.minecraft.world.item.ItemStack> getDropsForLeaf(net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, int, net.minecraft.world.level.block.Block);
  public boolean checkForDynamicSapling(net.minecraft.world.item.Item);
  public java.lang.Runnable getTreeBreakActionCompat(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.item.ItemStack, net.minecraft.core.BlockPos);
  public boolean plantDynamicSaplingCompat(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.item.ItemStack);
  public boolean hasFittingTreeFamilyCompat(net.minecraft.core.BlockPos, net.minecraft.core.BlockPos, net.minecraft.world.level.LevelAccessor);
}
