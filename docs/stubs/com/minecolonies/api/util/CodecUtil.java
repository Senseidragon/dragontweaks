Compiled from "CodecUtil.java"
public class com.minecolonies.api.util.CodecUtil {
  public static final com.mojang.serialization.Codec<net.minecraft.world.level.ChunkPos> CHUNK_POS;
  public static <T> java.util.function.Function<net.minecraft.nbt.CompoundTag, T> nbtDecoder(com.mojang.serialization.Codec<T>, org.slf4j.Logger, java.util.function.Supplier<T>);
  public static <T> java.util.function.Function<T, net.minecraft.nbt.CompoundTag> nbtEncoder(com.mojang.serialization.Codec<T>, org.slf4j.Logger, java.util.function.Supplier<net.minecraft.nbt.CompoundTag>);
  public static <T> com.mojang.serialization.Codec<java.util.Set<T>> set(com.mojang.serialization.Codec<T>, java.util.function.Function<java.util.Collection<T>, java.util.Set<T>>);
}
