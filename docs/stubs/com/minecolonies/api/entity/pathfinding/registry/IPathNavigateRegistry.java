Compiled from "IPathNavigateRegistry.java"
public interface com.minecolonies.api.entity.pathfinding.registry.IPathNavigateRegistry {
  public static com.minecolonies.api.entity.pathfinding.registry.IPathNavigateRegistry getInstance();
  public abstract com.minecolonies.api.entity.pathfinding.registry.IPathNavigateRegistry registerNewPathNavigate(java.util.function.Predicate<net.minecraft.world.entity.Mob>, java.util.function.Function<net.minecraft.world.entity.Mob, com.minecolonies.core.entity.pathfinding.navigation.AbstractAdvancedPathNavigate>);
  public abstract com.minecolonies.core.entity.pathfinding.navigation.AbstractAdvancedPathNavigate getNavigateFor(net.minecraft.world.entity.Mob);
}
