Compiled from "IColonyEntitySpawnEvent.java"
public interface com.minecolonies.api.colony.colonyEvents.IColonyEntitySpawnEvent extends com.minecolonies.api.colony.colonyEvents.IColonySpawnEvent {
  public default java.util.List<net.minecraft.world.entity.Entity> getEntities();
  public default void registerEntity(net.minecraft.world.entity.Entity);
  public default void unregisterEntity(net.minecraft.world.entity.Entity);
  public default void onEntityDeath(net.minecraft.world.entity.LivingEntity);
}
