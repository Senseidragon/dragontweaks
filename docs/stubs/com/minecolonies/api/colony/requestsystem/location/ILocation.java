Compiled from "ILocation.java"
public interface com.minecolonies.api.colony.requestsystem.location.ILocation {
  public abstract net.minecraft.core.BlockPos getInDimensionLocation();
  public abstract net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level> getDimension();
  public abstract boolean isReachableFromLocation(com.minecolonies.api.colony.requestsystem.location.ILocation);
}
