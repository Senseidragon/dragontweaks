Compiled from "ClientboundCustomSetTimePayload.java"
public final class net.neoforged.neoforge.network.payload.ClientboundCustomSetTimePayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ClientboundCustomSetTimePayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, net.neoforged.neoforge.network.payload.ClientboundCustomSetTimePayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.ClientboundCustomSetTimePayload(long, long, boolean, float, float);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ClientboundCustomSetTimePayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public long gameTime();
  public long dayTime();
  public boolean gameRule();
  public float dayTimeFraction();
  public float dayTimePerTick();
}
