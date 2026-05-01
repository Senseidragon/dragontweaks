Compiled from "HolderSetType.java"
public interface net.neoforged.neoforge.registries.holdersets.HolderSetType {
  public abstract <T> com.mojang.serialization.MapCodec<? extends net.neoforged.neoforge.registries.holdersets.ICustomHolderSet<T>> makeCodec(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>>, com.mojang.serialization.Codec<net.minecraft.core.Holder<T>>, boolean);
  public abstract <T> net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, ? extends net.neoforged.neoforge.registries.holdersets.ICustomHolderSet<T>> makeStreamCodec(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>>);
}
