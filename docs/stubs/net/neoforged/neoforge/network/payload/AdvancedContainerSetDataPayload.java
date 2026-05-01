Compiled from "AdvancedContainerSetDataPayload.java"
public final class net.neoforged.neoforge.network.payload.AdvancedContainerSetDataPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.AdvancedContainerSetDataPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, net.neoforged.neoforge.network.payload.AdvancedContainerSetDataPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.AdvancedContainerSetDataPayload(byte, short, int);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.AdvancedContainerSetDataPayload> type();
  public net.minecraft.network.protocol.game.ClientboundContainerSetDataPacket toVanillaPacket();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public byte containerId();
  public short dataId();
  public int value();
}
