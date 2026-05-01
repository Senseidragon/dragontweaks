Compiled from "ColonyConnection.java"
public class com.minecolonies.api.colony.connections.ColonyConnection {
  public com.minecolonies.api.colony.connections.DiplomacyStatus diplomacyStatus;
  public int id;
  public java.lang.String name;
  public net.minecraft.core.BlockPos pos;
  public com.minecolonies.api.colony.connections.ColonyConnection(int, java.lang.String, net.minecraft.core.BlockPos, com.minecolonies.api.colony.connections.DiplomacyStatus);
  public com.minecolonies.api.colony.connections.ColonyConnection();
  public net.minecraft.nbt.CompoundTag serializeNBT();
  public com.minecolonies.api.colony.connections.ColonyConnection deserializeNBT(net.minecraft.nbt.CompoundTag);
  public void serializeByteBuf(net.minecraft.network.FriendlyByteBuf);
  public com.minecolonies.api.colony.connections.ColonyConnection deserializeByteBuf(net.minecraft.network.FriendlyByteBuf);
}
