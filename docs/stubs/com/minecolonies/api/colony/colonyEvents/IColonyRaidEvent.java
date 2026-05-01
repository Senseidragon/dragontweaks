Compiled from "IColonyRaidEvent.java"
public interface com.minecolonies.api.colony.colonyEvents.IColonyRaidEvent extends com.minecolonies.api.colony.colonyEvents.IColonyEntitySpawnEvent {
  public abstract net.minecraft.world.entity.EntityType<?> getNormalRaiderType();
  public abstract net.minecraft.world.entity.EntityType<?> getArcherRaiderType();
  public abstract net.minecraft.world.entity.EntityType<?> getBossRaiderType();
  public abstract void addSpawner(net.minecraft.core.BlockPos);
  public abstract java.util.List<net.minecraft.core.BlockPos> getWayPoints();
  public default boolean isRaidActive();
}
