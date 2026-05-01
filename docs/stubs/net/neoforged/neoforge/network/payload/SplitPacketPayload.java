Compiled from "SplitPacketPayload.java"
public final class net.neoforged.neoforge.network.payload.SplitPacketPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.SplitPacketPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.SplitPacketPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.SplitPacketPayload(byte[]);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.SplitPacketPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public byte[] payload();
}
