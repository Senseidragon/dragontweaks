Compiled from "ColonyPlayerRankChangedModEvent.java"
public final class com.minecolonies.api.eventbus.events.colony.ColonyPlayerRankChangedModEvent extends com.minecolonies.api.eventbus.events.colony.AbstractColonyModEvent {
  public com.minecolonies.api.eventbus.events.colony.ColonyPlayerRankChangedModEvent(com.minecolonies.api.colony.IColony, com.minecolonies.api.colony.permissions.ColonyPlayer, com.minecolonies.api.colony.permissions.Rank, com.minecolonies.api.colony.permissions.Rank);
  public com.minecolonies.api.colony.permissions.ColonyPlayer getPlayer();
  public com.minecolonies.api.colony.permissions.Rank getNewRank();
  public com.minecolonies.api.colony.permissions.Rank getOldRank();
}
