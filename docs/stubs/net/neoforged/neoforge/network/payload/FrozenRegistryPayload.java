Compiled from "FrozenRegistryPayload.java"
public final class net.neoforged.neoforge.network.payload.FrozenRegistryPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.FrozenRegistryPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.FrozenRegistryPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.FrozenRegistryPayload(net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.registries.RegistrySnapshot);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.FrozenRegistryPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.resources.ResourceLocation registryName();
  public net.neoforged.neoforge.registries.RegistrySnapshot snapshot();
}
