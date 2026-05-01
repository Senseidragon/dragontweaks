Compiled from "KnownRegistryDataMapsPayload.java"
public final class net.neoforged.neoforge.network.payload.KnownRegistryDataMapsPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.KnownRegistryDataMapsPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.KnownRegistryDataMapsPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.KnownRegistryDataMapsPayload(java.util.Map<net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<?>>, java.util.List<net.neoforged.neoforge.network.payload.KnownRegistryDataMapsPayload$KnownDataMap>>);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.KnownRegistryDataMapsPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.Map<net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<?>>, java.util.List<net.neoforged.neoforge.network.payload.KnownRegistryDataMapsPayload$KnownDataMap>> dataMaps();
}
