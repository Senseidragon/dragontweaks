Compiled from "ModdedNetworkSetupFailedPayload.java"
public final class net.neoforged.neoforge.network.payload.ModdedNetworkSetupFailedPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.resources.ResourceLocation ID;
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ModdedNetworkSetupFailedPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.ModdedNetworkSetupFailedPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.ModdedNetworkSetupFailedPayload(java.util.Map<net.minecraft.resources.ResourceLocation, net.minecraft.network.chat.Component>);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ModdedNetworkSetupFailedPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.Map<net.minecraft.resources.ResourceLocation, net.minecraft.network.chat.Component> failureReasons();
}
