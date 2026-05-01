Compiled from "DataMapHooks.java"
public class net.neoforged.neoforge.common.DataMapHooks {
  public static boolean didHaveToFallbackToVanillaMaps;
  public static final java.util.Map<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.Block> INVERSE_OXIDIZABLES_DATAMAP;
  public static final java.util.Map<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.Block> INVERSE_WAXABLES_DATAMAP;
  public net.neoforged.neoforge.common.DataMapHooks();
  public static net.minecraft.world.level.block.Block getNextOxidizedStage(net.minecraft.world.level.block.Block);
  public static net.minecraft.world.level.block.Block getPreviousOxidizedStage(net.minecraft.world.level.block.Block);
  public static net.minecraft.world.level.block.Block getBlockWaxed(net.minecraft.world.level.block.Block);
  public static net.minecraft.world.level.block.Block getBlockUnwaxed(net.minecraft.world.level.block.Block);
}
