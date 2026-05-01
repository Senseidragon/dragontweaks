Compiled from "KnownRegistryDataMapsReplyPayload.java"
public final class net.neoforged.neoforge.network.payload.KnownRegistryDataMapsReplyPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.KnownRegistryDataMapsReplyPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.KnownRegistryDataMapsReplyPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.KnownRegistryDataMapsReplyPayload(java.util.Map<net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<?>>, java.util.Collection<net.minecraft.resources.ResourceLocation>>);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.KnownRegistryDataMapsReplyPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.Map<net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<?>>, java.util.Collection<net.minecraft.resources.ResourceLocation>> dataMaps();
}
