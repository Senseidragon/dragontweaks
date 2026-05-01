Compiled from "LenientUnboundedMapCodec.java"
public class net.neoforged.neoforge.common.LenientUnboundedMapCodec<K, V> implements com.mojang.serialization.codecs.BaseMapCodec<K, V>, com.mojang.serialization.Codec<java.util.Map<K, V>> {
  public net.neoforged.neoforge.common.LenientUnboundedMapCodec(com.mojang.serialization.Codec<K>, com.mojang.serialization.Codec<V>);
  public com.mojang.serialization.Codec<K> keyCodec();
  public com.mojang.serialization.Codec<V> elementCodec();
  public <T> com.mojang.serialization.DataResult<java.util.Map<K, V>> decode(com.mojang.serialization.DynamicOps<T>, com.mojang.serialization.MapLike<T>);
  public <T> com.mojang.serialization.DataResult<com.mojang.datafixers.util.Pair<java.util.Map<K, V>, T>> decode(com.mojang.serialization.DynamicOps<T>, T);
  public <T> com.mojang.serialization.DataResult<T> encode(java.util.Map<K, V>, com.mojang.serialization.DynamicOps<T>, T);
  public boolean equals(java.lang.Object);
  public int hashCode();
  public java.lang.String toString();
  public com.mojang.serialization.DataResult encode(java.lang.Object, com.mojang.serialization.DynamicOps, java.lang.Object);
}
