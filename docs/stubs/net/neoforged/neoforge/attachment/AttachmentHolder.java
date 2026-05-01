Compiled from "AttachmentHolder.java"
public abstract class net.neoforged.neoforge.attachment.AttachmentHolder implements net.neoforged.neoforge.attachment.IAttachmentHolder {
  public static final java.lang.String ATTACHMENTS_NBT_KEY;
  public net.neoforged.neoforge.attachment.AttachmentHolder();
  public final boolean hasAttachments();
  public final boolean hasData(net.neoforged.neoforge.attachment.AttachmentType<?>);
  public final <T> T getData(net.neoforged.neoforge.attachment.AttachmentType<T>);
  public <T> T getExistingDataOrNull(net.neoforged.neoforge.attachment.AttachmentType<T>);
  public <T> T setData(net.neoforged.neoforge.attachment.AttachmentType<T>, T);
  public <T> T removeData(net.neoforged.neoforge.attachment.AttachmentType<T>);
  public final net.minecraft.nbt.CompoundTag serializeAttachments(net.minecraft.core.HolderLookup$Provider);
}
