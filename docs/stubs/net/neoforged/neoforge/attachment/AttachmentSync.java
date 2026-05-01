Compiled from "AttachmentSync.java"
public final class net.neoforged.neoforge.attachment.AttachmentSync {
  public static final net.minecraft.core.Registry<net.neoforged.neoforge.attachment.AttachmentType<?>> SYNCED_ATTACHMENT_TYPES;
  public static final net.neoforged.neoforge.registries.callback.AddCallback<net.neoforged.neoforge.attachment.AttachmentType<?>> ATTACHMENT_TYPE_ADD_CALLBACK;
  public static void syncBlockEntityUpdate(net.minecraft.world.level.block.entity.BlockEntity, net.neoforged.neoforge.attachment.AttachmentType<?>);
  public static void syncChunkUpdate(net.minecraft.world.level.chunk.LevelChunk, net.neoforged.neoforge.attachment.AttachmentHolder$AsField, net.neoforged.neoforge.attachment.AttachmentType<?>);
  public static void syncEntityUpdate(net.minecraft.world.entity.Entity, net.neoforged.neoforge.attachment.AttachmentType<?>);
  public static void syncLevelUpdate(net.minecraft.server.level.ServerLevel, net.neoforged.neoforge.attachment.AttachmentType<?>);
  public static void onChunkSent(net.neoforged.neoforge.event.level.ChunkWatchEvent$Sent);
  public static void syncInitialEntityAttachments(net.minecraft.world.entity.Entity, net.minecraft.server.level.ServerPlayer, java.util.function.Consumer<net.minecraft.network.protocol.Packet<? super net.minecraft.network.protocol.game.ClientGamePacketListener>>);
  public static void syncInitialPlayerAttachments(net.minecraft.server.level.ServerPlayer);
  public static void syncInitialLevelAttachments(net.minecraft.server.level.ServerLevel, net.minecraft.server.level.ServerPlayer);
  public static void receiveSyncedDataAttachments(net.neoforged.neoforge.attachment.AttachmentHolder, net.minecraft.core.RegistryAccess, java.util.List<net.neoforged.neoforge.attachment.AttachmentType<?>>, byte[]);
}
