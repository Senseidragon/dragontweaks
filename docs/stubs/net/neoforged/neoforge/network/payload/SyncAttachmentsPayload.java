Compiled from "SyncAttachmentsPayload.java"
public final class net.neoforged.neoforge.network.payload.SyncAttachmentsPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.SyncAttachmentsPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, net.neoforged.neoforge.network.payload.SyncAttachmentsPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.SyncAttachmentsPayload(net.neoforged.neoforge.network.payload.SyncAttachmentsPayload$Target, java.util.List<net.neoforged.neoforge.attachment.AttachmentType<?>>, byte[]);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<? extends net.minecraft.network.protocol.common.custom.CustomPacketPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.neoforged.neoforge.network.payload.SyncAttachmentsPayload$Target target();
  public java.util.List<net.neoforged.neoforge.attachment.AttachmentType<?>> types();
  public byte[] syncPayload();
}
