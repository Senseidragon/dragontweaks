Compiled from "IPathJob.java"
public interface com.minecolonies.api.entity.pathfinding.IPathJob extends java.util.concurrent.Callable<net.minecraft.world.level.pathfinder.Path> {
  public abstract com.minecolonies.core.entity.pathfinding.pathresults.PathResult getResult();
  public abstract com.minecolonies.core.entity.pathfinding.PathingOptions getPathingOptions();
  public abstract net.minecraft.world.entity.Mob getEntity();
  public abstract net.minecraft.world.level.Level getActualWorld();
  public abstract net.minecraft.core.BlockPos getStart();
}
