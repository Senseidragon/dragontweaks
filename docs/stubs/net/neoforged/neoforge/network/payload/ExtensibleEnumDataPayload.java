Compiled from "ExtensibleEnumDataPayload.java"
public final class net.neoforged.neoforge.network.payload.ExtensibleEnumDataPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ExtensibleEnumDataPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<io.netty.buffer.ByteBuf, net.neoforged.neoforge.network.payload.ExtensibleEnumDataPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.ExtensibleEnumDataPayload(java.util.Map<java.lang.String, net.neoforged.neoforge.network.configuration.CheckExtensibleEnums$EnumEntry>);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.ExtensibleEnumDataPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.Map<java.lang.String, net.neoforged.neoforge.network.configuration.CheckExtensibleEnums$EnumEntry> enumEntries();
}
