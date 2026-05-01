Compiled from "INBTSerializable.java"
public interface net.neoforged.neoforge.common.util.INBTSerializable<T extends net.minecraft.nbt.Tag> {
  public abstract T serializeNBT(net.minecraft.core.HolderLookup$Provider);
  public abstract void deserializeNBT(net.minecraft.core.HolderLookup$Provider, T);
}
