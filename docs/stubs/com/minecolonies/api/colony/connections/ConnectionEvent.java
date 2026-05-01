Compiled from "ConnectionEvent.java"
public final class com.minecolonies.api.colony.connections.ConnectionEvent extends java.lang.Record {
  public com.minecolonies.api.colony.connections.ConnectionEvent(int, java.lang.String, com.minecolonies.api.colony.connections.ConnectionEventType);
  public net.minecraft.nbt.CompoundTag serializeNBT();
  public void serializeByteBuf(net.minecraft.network.FriendlyByteBuf);
  public static com.minecolonies.api.colony.connections.ConnectionEvent deserializeNBT(net.minecraft.nbt.CompoundTag);
  public static com.minecolonies.api.colony.connections.ConnectionEvent deserializeByteBuf(net.minecraft.network.FriendlyByteBuf);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public int id();
  public java.lang.String name();
  public com.minecolonies.api.colony.connections.ConnectionEventType connectionEventType();
}
