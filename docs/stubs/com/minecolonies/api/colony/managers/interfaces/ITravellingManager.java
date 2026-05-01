Compiled from "ITravellingManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.ITravellingManager {
  public default boolean isTravelling(com.minecolonies.api.colony.ICitizenData);
  public default boolean isTravelling(com.minecolonies.api.colony.ICitizenDataView);
  public abstract boolean isTravelling(int);
  public default java.util.Optional<net.minecraft.core.BlockPos> getTravellingTargetFor(com.minecolonies.api.colony.ICitizenData);
  public abstract java.util.Optional<net.minecraft.core.BlockPos> getTravellingTargetFor(int);
  public default void startTravellingTo(com.minecolonies.api.colony.ICitizenData, net.minecraft.core.BlockPos, int);
  public abstract void startTravellingTo(int, net.minecraft.core.BlockPos, int);
  public default void finishTravellingFor(com.minecolonies.api.colony.ICitizenData);
  public abstract void finishTravellingFor(int);
  public abstract void recallAllTravellingCitizens();
}
