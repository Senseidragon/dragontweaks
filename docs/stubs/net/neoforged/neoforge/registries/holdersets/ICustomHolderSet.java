Compiled from "ICustomHolderSet.java"
public interface net.neoforged.neoforge.registries.holdersets.ICustomHolderSet<T> extends net.minecraft.core.HolderSet<T> {
  public abstract net.neoforged.neoforge.registries.holdersets.HolderSetType type();
  public default net.neoforged.neoforge.common.extensions.IHolderSetExtension$SerializationType serializationType();
}
