Compiled from "CommonRegisterPayload.java"
public final class net.neoforged.neoforge.network.payload.CommonRegisterPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.resources.ResourceLocation ID;
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.CommonRegisterPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.CommonRegisterPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.CommonRegisterPayload(int, net.minecraft.network.ConnectionProtocol, java.util.Set<net.minecraft.resources.ResourceLocation>);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.CommonRegisterPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public int version();
  public net.minecraft.network.ConnectionProtocol protocol();
  public java.util.Set<net.minecraft.resources.ResourceLocation> channels();
}
