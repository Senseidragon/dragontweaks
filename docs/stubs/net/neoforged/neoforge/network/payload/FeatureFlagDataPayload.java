Compiled from "FeatureFlagDataPayload.java"
public final class net.neoforged.neoforge.network.payload.FeatureFlagDataPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.FeatureFlagDataPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<io.netty.buffer.ByteBuf, net.neoforged.neoforge.network.payload.FeatureFlagDataPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.FeatureFlagDataPayload(java.util.Set<net.minecraft.resources.ResourceLocation>);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.FeatureFlagDataPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.Set<net.minecraft.resources.ResourceLocation> moddedFlags();
}
