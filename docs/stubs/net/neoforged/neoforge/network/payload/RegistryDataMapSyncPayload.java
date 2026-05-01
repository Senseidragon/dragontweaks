Compiled from "RegistryDataMapSyncPayload.java"
public final class net.neoforged.neoforge.network.payload.RegistryDataMapSyncPayload<T> extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.RegistryDataMapSyncPayload<?>> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, net.neoforged.neoforge.network.payload.RegistryDataMapSyncPayload<?>> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.RegistryDataMapSyncPayload(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>>, java.util.Map<net.minecraft.resources.ResourceLocation, java.util.Map<net.minecraft.resources.ResourceKey<T>, ?>>);
  public static <T> net.neoforged.neoforge.network.payload.RegistryDataMapSyncPayload<T> decode(net.minecraft.network.RegistryFriendlyByteBuf);
  public void write(net.minecraft.network.RegistryFriendlyByteBuf);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.RegistryDataMapSyncPayload<?>> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>> registryKey();
  public java.util.Map<net.minecraft.resources.ResourceLocation, java.util.Map<net.minecraft.resources.ResourceKey<T>, ?>> dataMaps();
}
