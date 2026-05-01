Compiled from "ColonyUtils.java"
public final class com.minecolonies.api.util.ColonyUtils {
  public static java.util.concurrent.CompletableFuture<com.ldtteam.structurize.blueprints.v1.Blueprint> queueBlueprintLoad(net.minecraft.world.level.Level, java.lang.String, java.lang.String, java.util.function.Consumer<com.ldtteam.structurize.blueprints.v1.Blueprint>);
  public static java.util.concurrent.CompletableFuture<com.ldtteam.structurize.blueprints.v1.Blueprint> queueBlueprintLoad(net.minecraft.world.level.Level, java.lang.String, java.lang.String, java.util.function.Consumer<com.ldtteam.structurize.blueprints.v1.Blueprint>, java.util.function.Consumer<java.lang.String>);
  public static net.minecraft.util.Tuple<net.minecraft.core.BlockPos, net.minecraft.core.BlockPos> calculateCorners(net.minecraft.core.BlockPos, net.minecraft.world.level.Level, com.ldtteam.structurize.blueprints.v1.Blueprint, com.ldtteam.structurize.api.RotationMirror);
  public static net.minecraft.util.Tuple<net.minecraft.core.BlockPos, net.minecraft.core.BlockPos> calculateCorners(net.minecraft.world.phys.AABB);
  public static int getOwningColony(net.minecraft.world.level.chunk.ChunkAccess);
  public static java.util.Map<java.lang.Integer, java.util.Set<net.minecraft.core.BlockPos>> getAllClaimingBuildings(net.minecraft.world.level.chunk.ChunkAccess);
  public static java.util.List<java.lang.Integer> getStaticClaims(net.minecraft.world.level.chunk.ChunkAccess);
  public static com.minecolonies.api.util.ChunkCapData getChunkCapData(net.minecraft.world.level.chunk.ChunkAccess);
}
