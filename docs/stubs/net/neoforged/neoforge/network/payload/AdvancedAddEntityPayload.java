Compiled from "AdvancedAddEntityPayload.java"
public final class net.neoforged.neoforge.network.payload.AdvancedAddEntityPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.AdvancedAddEntityPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.AdvancedAddEntityPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.AdvancedAddEntityPayload(net.minecraft.world.entity.Entity);
  public net.neoforged.neoforge.network.payload.AdvancedAddEntityPayload(int, byte[]);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.AdvancedAddEntityPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public int entityId();
  public byte[] customPayload();
}
