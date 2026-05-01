Compiled from "WorldUtil.java"
public class com.minecolonies.api.util.WorldUtil {
  public com.minecolonies.api.util.WorldUtil();
  public static boolean isBlockLoaded(net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos);
  public static boolean isChunkLoaded(net.minecraft.world.level.LevelAccessor, int, int);
  public static void markChunkDirty(net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public static boolean isChunkLoaded(net.minecraft.world.level.LevelAccessor, net.minecraft.world.level.ChunkPos);
  public static boolean isEntityBlockLoaded(net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos);
  public static boolean isEntityChunkLoaded(net.minecraft.world.level.LevelAccessor, int, int);
  public static boolean isEntityChunkLoaded(net.minecraft.world.level.LevelAccessor, net.minecraft.world.level.ChunkPos);
  public static boolean isAABBLoaded(net.minecraft.world.level.Level, net.minecraft.world.phys.AABB);
  public static boolean isDayTime(net.minecraft.world.level.Level);
  public static boolean isPastTime(net.minecraft.world.level.Level, int);
  public static boolean isPastNoon(net.minecraft.world.level.Level);
  public static boolean isOverworldType(net.minecraft.world.level.Level);
  public static boolean isNetherType(net.minecraft.world.level.Level);
  public static boolean isOfWorldType(net.minecraft.world.level.Level, net.minecraft.resources.ResourceKey<net.minecraft.world.level.dimension.DimensionType>);
  public static boolean isPeaceful(net.minecraft.world.level.Level);
  public static boolean setBlockState(net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public static boolean setBlockState(net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, int);
  public static boolean removeBlock(net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos, boolean);
  public static <T extends net.minecraft.world.entity.Entity> java.util.List<? extends T> getEntitiesWithinBuilding(net.minecraft.world.level.Level, java.lang.Class<? extends T>, com.minecolonies.api.colony.buildings.IBuilding, java.util.function.Predicate<? super T>);
  public static int getDimensionMaxHeight(net.minecraft.world.level.dimension.DimensionType);
  public static int getDimensionMinHeight(net.minecraft.world.level.dimension.DimensionType);
  public static boolean isInWorldHeight(int, net.minecraft.world.level.Level);
  public static net.minecraft.world.entity.player.Player getNearestPlayer(net.minecraft.world.entity.Mob, int, int, int, double);
  public static <T extends net.minecraft.world.entity.LivingEntity> T getNearestEntity(java.util.List<? extends T>, net.minecraft.world.entity.Mob, int, int, int, double);
}
