Compiled from "ConfigFilePayload.java"
public final class net.neoforged.neoforge.network.payload.ConfigFilePayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ConfigFilePayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.ConfigFilePayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.ConfigFilePayload(java.lang.String, byte[]);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ConfigFilePayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.lang.String fileName();
  public byte[] contents();
}
