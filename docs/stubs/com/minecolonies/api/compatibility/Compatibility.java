Compiled from "Compatibility.java"
public final class com.minecolonies.api.compatibility.Compatibility {
  public static com.minecolonies.api.compatibility.IJeiProxy jeiProxy;
  public static com.minecolonies.api.compatibility.resourcefulbees.IBeehiveCompat beeHiveCompat;
  public static com.minecolonies.api.compatibility.tinkers.SlimeTreeProxy tinkersSlimeCompat;
  public static com.minecolonies.api.compatibility.tinkers.TinkersToolProxy tinkersCompat;
  public static com.minecolonies.api.compatibility.dynamictrees.DynamicTreeProxy dynamicTreesCompat;
  public static boolean getMiningLevelCompatibility(net.minecraft.world.item.ItemStack, java.lang.String);
  public static boolean isSlimeBlock(net.minecraft.world.level.block.Block);
  public static boolean isSlimeLeaf(net.minecraft.world.level.block.Block);
  public static boolean isSlimeSapling(net.minecraft.world.level.block.Block);
  public static boolean isSlimeDirtOrGrass(net.minecraft.world.level.block.Block);
  public static int getLeafVariant(net.minecraft.world.level.block.state.BlockState);
  public static boolean isTinkersWeapon(net.minecraft.world.item.ItemStack);
  public static boolean isTinkersTool(net.minecraft.world.item.ItemStack, com.minecolonies.api.equipment.registry.EquipmentTypeEntry);
  public static double getAttackDamage(net.minecraft.world.item.ItemStack);
  public static int getToolLevel(net.minecraft.world.item.ItemStack);
  public static boolean isDynTreePresent();
  public static boolean isDynamicBlock(net.minecraft.world.level.block.Block);
  public static boolean isDynamicLeaf(net.minecraft.world.level.block.Block);
  public static boolean isDynamicTrunkShell(net.minecraft.world.level.block.Block);
  public static net.minecraft.core.NonNullList<net.minecraft.world.item.ItemStack> getDropsForDynamicLeaf(net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, int, net.minecraft.world.level.block.Block);
  public static boolean plantDynamicSapling(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.item.ItemStack);
  public static java.lang.Runnable getDynamicTreeBreakAction(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.item.ItemStack, net.minecraft.core.BlockPos);
  public static boolean isDynamicTreeSapling(net.minecraft.world.item.ItemStack);
  public static boolean isDynamicFamilyFitting(net.minecraft.core.BlockPos, net.minecraft.core.BlockPos, net.minecraft.world.level.LevelAccessor);
  public static java.util.List<net.minecraft.world.item.ItemStack> getCombsFromHive(net.minecraft.core.BlockPos, net.minecraft.world.level.Level, int);
}
