Compiled from "IMinecoloniesNavigator.java"
public interface com.minecolonies.api.entity.pathfinding.IMinecoloniesNavigator {
  public abstract <T extends com.minecolonies.core.entity.pathfinding.pathjobs.AbstractPathJob> com.minecolonies.core.entity.pathfinding.pathresults.PathResult<T> setPathJob(com.minecolonies.core.entity.pathfinding.pathjobs.AbstractPathJob, net.minecraft.core.BlockPos, double, boolean);
  public abstract void recalc();
  public abstract com.minecolonies.core.entity.pathfinding.pathresults.PathResult getPathResult();
  public abstract net.minecraft.core.BlockPos getSafeDestination();
  public abstract net.minecraft.world.entity.Mob getOurEntity();
  public abstract void setPauseTicks(int);
  public abstract com.minecolonies.api.entity.pathfinding.IStuckHandler<com.minecolonies.core.entity.pathfinding.navigation.MinecoloniesAdvancedPathNavigate> getStuckHandler();
}
