Compiled from "TimeTracker.java"
public class net.neoforged.neoforge.server.timings.TimeTracker<T> {
  public static final net.neoforged.neoforge.server.timings.TimeTracker<net.minecraft.world.level.block.entity.BlockEntity> BLOCK_ENTITY_UPDATE;
  public static final net.neoforged.neoforge.server.timings.TimeTracker<net.minecraft.world.entity.Entity> ENTITY_UPDATE;
  public net.neoforged.neoforge.server.timings.TimeTracker();
  public com.google.common.collect.ImmutableList<net.neoforged.neoforge.server.timings.ObjectTimings<T>> getTimingData();
  public void reset();
  public void trackEnd(T);
  public void enable(int);
  public void trackStart(T);
}
