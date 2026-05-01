Compiled from "NeoForgeStreamCodecs.java"
public final class net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs {
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, byte[]> UNBOUNDED_BYTE_ARRAY;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.minecraft.world.level.ChunkPos> CHUNK_POS;
  public net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs();
  public static <B, V> net.minecraft.network.codec.StreamCodec<B, V> lazy(java.util.function.Supplier<net.minecraft.network.codec.StreamCodec<B, V>>);
  public static <B extends net.minecraft.network.FriendlyByteBuf, V extends java.lang.Enum<V>> net.minecraft.network.codec.StreamCodec<B, V> enumCodec(java.lang.Class<V>);
  public static <B extends net.minecraft.network.FriendlyByteBuf> net.minecraft.network.codec.StreamCodec<B, net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<?>>> registryKey();
  public static <V> net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, V> connectionAware(net.minecraft.network.codec.StreamCodec<? super net.minecraft.network.RegistryFriendlyByteBuf, V>, net.minecraft.network.codec.StreamCodec<? super net.minecraft.network.RegistryFriendlyByteBuf, V>);
  public static <B, V> net.minecraft.network.codec.StreamCodec<B, V> uncheckedUnit(V);
  public static <B, C, T1, T2, T3, T4, T5, T6, T7> net.minecraft.network.codec.StreamCodec<B, C> composite(net.minecraft.network.codec.StreamCodec<? super B, T1>, java.util.function.Function<C, T1>, net.minecraft.network.codec.StreamCodec<? super B, T2>, java.util.function.Function<C, T2>, net.minecraft.network.codec.StreamCodec<? super B, T3>, java.util.function.Function<C, T3>, net.minecraft.network.codec.StreamCodec<? super B, T4>, java.util.function.Function<C, T4>, net.minecraft.network.codec.StreamCodec<? super B, T5>, java.util.function.Function<C, T5>, net.minecraft.network.codec.StreamCodec<? super B, T6>, java.util.function.Function<C, T6>, net.minecraft.network.codec.StreamCodec<? super B, T7>, java.util.function.Function<C, T7>, com.mojang.datafixers.util.Function7<T1, T2, T3, T4, T5, T6, T7, C>);
}
