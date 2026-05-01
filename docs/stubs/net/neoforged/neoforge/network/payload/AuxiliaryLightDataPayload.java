Compiled from "AuxiliaryLightDataPayload.java"
public final class net.neoforged.neoforge.network.payload.AuxiliaryLightDataPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.AuxiliaryLightDataPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, net.neoforged.neoforge.network.payload.AuxiliaryLightDataPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.AuxiliaryLightDataPayload(net.minecraft.world.level.ChunkPos, java.util.Map<net.minecraft.core.BlockPos, java.lang.Byte>);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.AuxiliaryLightDataPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.world.level.ChunkPos pos();
  public java.util.Map<net.minecraft.core.BlockPos, java.lang.Byte> entries();
}
