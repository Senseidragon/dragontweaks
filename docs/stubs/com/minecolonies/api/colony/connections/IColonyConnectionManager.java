Compiled from "IColonyConnectionManager.java"
public interface com.minecolonies.api.colony.connections.IColonyConnectionManager extends net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public abstract boolean addNewConnectionNode(net.minecraft.core.BlockPos);
  public abstract void removeConnectionNode(net.minecraft.core.BlockPos);
  public abstract void tick();
  public abstract java.util.TreeMap<java.lang.Integer, com.minecolonies.api.colony.connections.ColonyConnection> getDirectlyConnectedColonies();
  public abstract java.util.TreeMap<java.lang.Integer, com.minecolonies.api.colony.connections.ColonyConnection> getIndirectlyConnectedColonies();
  public abstract com.minecolonies.api.colony.connections.ColonyConnectionNode getNode(net.minecraft.core.BlockPos);
  public abstract void addNewGateHouse(net.minecraft.core.BlockPos);
  public abstract void removeGateHouse(net.minecraft.core.BlockPos);
  public abstract boolean attemptEstablishConnection(net.minecraft.core.BlockPos, com.minecolonies.api.colony.IColony);
  public abstract void serializeToView(net.minecraft.network.FriendlyByteBuf);
  public abstract void deserializeFromView(net.minecraft.network.FriendlyByteBuf);
  public abstract void triggerConnectionEvent(com.minecolonies.api.colony.connections.ConnectionEvent);
  public abstract java.util.List<com.minecolonies.api.colony.connections.ConnectionEvent> getConnectionEvents();
  public abstract com.minecolonies.api.colony.connections.DiplomacyStatus getColonyDiplomacyStatus(int);
}
