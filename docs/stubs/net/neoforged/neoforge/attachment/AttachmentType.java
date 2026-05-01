Compiled from "AttachmentType.java"
public final class net.neoforged.neoforge.attachment.AttachmentType<T> {
  public static <T> net.neoforged.neoforge.attachment.AttachmentType$Builder<T> builder(java.util.function.Supplier<T>);
  public static <T> net.neoforged.neoforge.attachment.AttachmentType$Builder<T> builder(java.util.function.Function<net.neoforged.neoforge.attachment.IAttachmentHolder, T>);
  public static <S extends net.minecraft.nbt.Tag, T extends net.neoforged.neoforge.common.util.INBTSerializable<S>> net.neoforged.neoforge.attachment.AttachmentType$Builder<T> serializable(java.util.function.Supplier<T>);
  public static <S extends net.minecraft.nbt.Tag, T extends net.neoforged.neoforge.common.util.INBTSerializable<S>> net.neoforged.neoforge.attachment.AttachmentType$Builder<T> serializable(java.util.function.Function<net.neoforged.neoforge.attachment.IAttachmentHolder, T>);
}
