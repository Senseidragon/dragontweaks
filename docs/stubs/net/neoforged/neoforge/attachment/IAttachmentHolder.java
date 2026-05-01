Compiled from "IAttachmentHolder.java"
public interface net.neoforged.neoforge.attachment.IAttachmentHolder {
  public abstract boolean hasAttachments();
  public abstract boolean hasData(net.neoforged.neoforge.attachment.AttachmentType<?>);
  public default <T> boolean hasData(java.util.function.Supplier<net.neoforged.neoforge.attachment.AttachmentType<T>>);
  public abstract <T> T getData(net.neoforged.neoforge.attachment.AttachmentType<T>);
  public default <T> T getData(java.util.function.Supplier<net.neoforged.neoforge.attachment.AttachmentType<T>>);
  public default <T> java.util.Optional<T> getExistingData(net.neoforged.neoforge.attachment.AttachmentType<T>);
  public default <T> java.util.Optional<T> getExistingData(java.util.function.Supplier<net.neoforged.neoforge.attachment.AttachmentType<T>>);
  public default <T> T getExistingDataOrNull(net.neoforged.neoforge.attachment.AttachmentType<T>);
  public default <T> T getExistingDataOrNull(java.util.function.Supplier<net.neoforged.neoforge.attachment.AttachmentType<T>>);
  public abstract <T> T setData(net.neoforged.neoforge.attachment.AttachmentType<T>, T);
  public default <T> T setData(java.util.function.Supplier<net.neoforged.neoforge.attachment.AttachmentType<T>>, T);
  public abstract <T> T removeData(net.neoforged.neoforge.attachment.AttachmentType<T>);
  public default <T> T removeData(java.util.function.Supplier<net.neoforged.neoforge.attachment.AttachmentType<T>>);
  public default void syncData(net.neoforged.neoforge.attachment.AttachmentType<?>);
  public default void syncData(java.util.function.Supplier<? extends net.neoforged.neoforge.attachment.AttachmentType<?>>);
}
