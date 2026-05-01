Compiled from "ForcedChunkManager.java"
public class net.neoforged.neoforge.common.world.chunk.ForcedChunkManager {
  public net.neoforged.neoforge.common.world.chunk.ForcedChunkManager();
  public static synchronized void init();
  public static boolean hasForcedChunks(net.minecraft.server.level.ServerLevel);
  public static void reinstatePersistentChunks(net.minecraft.server.level.ServerLevel, net.minecraft.world.level.ForcedChunksSavedData);
  public static void writeModForcedChunks(net.minecraft.nbt.CompoundTag, net.neoforged.neoforge.common.world.chunk.ForcedChunkManager$TicketTracker<net.minecraft.core.BlockPos>, net.neoforged.neoforge.common.world.chunk.ForcedChunkManager$TicketTracker<java.util.UUID>);
  public static void readModForcedChunks(net.minecraft.nbt.CompoundTag, net.neoforged.neoforge.common.world.chunk.ForcedChunkManager$TicketTracker<net.minecraft.core.BlockPos>, net.neoforged.neoforge.common.world.chunk.ForcedChunkManager$TicketTracker<java.util.UUID>);
  public static net.minecraft.nbt.CompoundTag writeBlockPos(net.minecraft.core.BlockPos);
}
