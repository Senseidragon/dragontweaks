Compiled from "GenerationTask.java"
public class net.neoforged.neoforge.server.command.generation.GenerationTask {
  public static final net.minecraft.server.level.TicketType<net.minecraft.world.level.ChunkPos> NEOFORGE_GENERATE_FORCED;
  public net.neoforged.neoforge.server.command.generation.GenerationTask(net.minecraft.server.level.ServerLevel, int, int, int);
  public int getOkCount();
  public int getErrorCount();
  public int getSkippedCount();
  public int getTotalCount();
  public void run(net.neoforged.neoforge.server.command.generation.GenerationTask$Listener);
  public void stop();
}
