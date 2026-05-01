Compiled from "IFriendlyByteBufExtension.java"
public interface net.neoforged.neoforge.common.extensions.IFriendlyByteBufExtension {
  public default <T> void writeObjectCollection(java.util.Collection<T>, java.util.function.BiConsumer<T, net.minecraft.network.FriendlyByteBuf>);
  public default <T> T[] readArray(java.util.function.IntFunction<T[]>, net.minecraft.network.codec.StreamDecoder<? super net.minecraft.network.FriendlyByteBuf, T>);
  public default <T> net.minecraft.network.FriendlyByteBuf writeArray(T[], net.minecraft.network.codec.StreamEncoder<? super net.minecraft.network.FriendlyByteBuf, T>);
  public default net.minecraft.network.FriendlyByteBuf writeByte(byte);
  public default <K, V> java.util.Map<K, V> readMap(net.minecraft.network.codec.StreamDecoder<? super net.minecraft.network.FriendlyByteBuf, K>, java.util.function.BiFunction<net.minecraft.network.FriendlyByteBuf, K, V>);
  public default <K, V> void writeMap(java.util.Map<K, V>, net.minecraft.network.codec.StreamEncoder<? super net.minecraft.network.FriendlyByteBuf, K>, org.apache.commons.lang3.function.TriConsumer<net.minecraft.network.FriendlyByteBuf, K, V>);
}
