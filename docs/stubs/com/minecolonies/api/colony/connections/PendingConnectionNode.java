Compiled from "PendingConnectionNode.java"
public class com.minecolonies.api.colony.connections.PendingConnectionNode extends com.minecolonies.api.colony.connections.ColonyConnectionNode {
  public com.minecolonies.api.colony.connections.PendingConnectionNode(net.minecraft.core.BlockPos, com.minecolonies.core.entity.pathfinding.pathresults.PathResult<com.minecolonies.core.entity.pathfinding.pathjobs.PathJobMoveToLocation>, com.minecolonies.api.colony.connections.PendingConnectionNode$PendingConnectionType);
  public com.minecolonies.api.colony.connections.PendingConnectionNode(net.minecraft.core.BlockPos);
  public net.minecraft.nbt.CompoundTag write();
  public void read(net.minecraft.nbt.CompoundTag);
  public com.minecolonies.api.colony.connections.PendingConnectionNode$PendingConnectionType getPendingConnectionType();
  public void setCachedPathResult(com.minecolonies.core.entity.pathfinding.pathresults.PathResult<com.minecolonies.core.entity.pathfinding.pathjobs.PathJobMoveToLocation>);
  public com.minecolonies.core.entity.pathfinding.pathresults.PathResult<com.minecolonies.core.entity.pathfinding.pathjobs.PathJobMoveToLocation> getCachedPathResult();
}
