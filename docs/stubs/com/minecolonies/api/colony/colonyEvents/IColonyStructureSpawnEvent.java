Compiled from "IColonyStructureSpawnEvent.java"
public interface com.minecolonies.api.colony.colonyEvents.IColonyStructureSpawnEvent extends com.minecolonies.api.colony.colonyEvents.IColonyEvent {
  public abstract java.util.List<com.minecolonies.api.util.Tuple<java.lang.String, net.minecraft.core.BlockPos>> getSchematicSpawns();
  public abstract java.lang.String getShipDesc();
  public abstract void setMaxRaiderCount(int);
}
