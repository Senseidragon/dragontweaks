Compiled from "IStuckHandler.java"
public interface com.minecolonies.api.entity.pathfinding.IStuckHandler<NAV extends net.minecraft.world.entity.ai.navigation.PathNavigation & com.minecolonies.api.entity.pathfinding.IMinecoloniesNavigator> {
  public abstract void checkStuck(NAV);
  public abstract void resetGlobalStuckTimers();
  public abstract int getStuckLevel();
}
