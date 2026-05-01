Compiled from "CommonVersionPayload.java"
public final class net.neoforged.neoforge.network.payload.CommonVersionPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.resources.ResourceLocation ID;
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.CommonVersionPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.CommonVersionPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.CommonVersionPayload();
  public net.neoforged.neoforge.network.payload.CommonVersionPayload(java.util.List<java.lang.Integer>);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.CommonVersionPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.List<java.lang.Integer> versions();
}
