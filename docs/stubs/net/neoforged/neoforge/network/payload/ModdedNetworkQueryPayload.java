Compiled from "ModdedNetworkQueryPayload.java"
public final class net.neoforged.neoforge.network.payload.ModdedNetworkQueryPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.resources.ResourceLocation ID;
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ModdedNetworkQueryPayload> TYPE;
  public static net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.ModdedNetworkQueryPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.ModdedNetworkQueryPayload(java.util.Map<net.minecraft.network.ConnectionProtocol, java.util.Set<net.neoforged.neoforge.network.payload.ModdedNetworkQueryComponent>>);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ModdedNetworkQueryPayload> type();
  public static net.neoforged.neoforge.network.payload.ModdedNetworkQueryPayload fromRegistry(java.util.Map<net.minecraft.network.ConnectionProtocol, java.util.Map<net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.network.registration.PayloadRegistration<?>>>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.Map<net.minecraft.network.ConnectionProtocol, java.util.Set<net.neoforged.neoforge.network.payload.ModdedNetworkQueryComponent>> queries();
}
