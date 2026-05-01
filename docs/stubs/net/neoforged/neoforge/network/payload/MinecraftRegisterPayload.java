Compiled from "MinecraftRegisterPayload.java"
public final class net.neoforged.neoforge.network.payload.MinecraftRegisterPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.resources.ResourceLocation ID;
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.MinecraftRegisterPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.MinecraftRegisterPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.MinecraftRegisterPayload(java.util.Set<net.minecraft.resources.ResourceLocation>);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.MinecraftRegisterPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.Set<net.minecraft.resources.ResourceLocation> newChannels();
}
