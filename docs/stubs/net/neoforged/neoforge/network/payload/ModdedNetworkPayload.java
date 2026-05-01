Compiled from "ModdedNetworkPayload.java"
public final class net.neoforged.neoforge.network.payload.ModdedNetworkPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.resources.ResourceLocation ID;
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ModdedNetworkPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.ModdedNetworkPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.ModdedNetworkPayload(net.neoforged.neoforge.network.registration.NetworkPayloadSetup);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ModdedNetworkPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.neoforged.neoforge.network.registration.NetworkPayloadSetup setup();
}
