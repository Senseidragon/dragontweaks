Compiled from "IAttachmentSerializer.java"
public interface net.neoforged.neoforge.attachment.IAttachmentSerializer<S extends net.minecraft.nbt.Tag, T> {
  public abstract T read(net.neoforged.neoforge.attachment.IAttachmentHolder, S, net.minecraft.core.HolderLookup$Provider);
  public abstract S write(T, net.minecraft.core.HolderLookup$Provider);
}
