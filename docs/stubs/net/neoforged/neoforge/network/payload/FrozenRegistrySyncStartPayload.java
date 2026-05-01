Compiled from "FrozenRegistrySyncStartPayload.java"
public final class net.neoforged.neoforge.network.payload.FrozenRegistrySyncStartPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.FrozenRegistrySyncStartPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.FrozenRegistrySyncStartPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.FrozenRegistrySyncStartPayload(java.util.List<net.minecraft.resources.ResourceLocation>);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.FrozenRegistrySyncStartPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.List<net.minecraft.resources.ResourceLocation> toAccess();
}
