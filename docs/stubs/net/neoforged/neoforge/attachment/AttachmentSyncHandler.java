Compiled from "AttachmentSyncHandler.java"
public interface net.neoforged.neoforge.attachment.AttachmentSyncHandler<T> {
  public default boolean sendToPlayer(net.neoforged.neoforge.attachment.IAttachmentHolder, net.minecraft.server.level.ServerPlayer);
  public abstract void write(net.minecraft.network.RegistryFriendlyByteBuf, T, boolean);
  public abstract T read(net.neoforged.neoforge.attachment.IAttachmentHolder, net.minecraft.network.RegistryFriendlyByteBuf, T);
}
