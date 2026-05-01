Compiled from "MutableHashedLinkedMap.java"
public class net.neoforged.neoforge.common.util.MutableHashedLinkedMap<K, V> implements java.lang.Iterable<java.util.Map$Entry<K, V>> {
  public net.neoforged.neoforge.common.util.MutableHashedLinkedMap();
  public net.neoforged.neoforge.common.util.MutableHashedLinkedMap(it.unimi.dsi.fastutil.Hash$Strategy<? super K>);
  public net.neoforged.neoforge.common.util.MutableHashedLinkedMap(it.unimi.dsi.fastutil.Hash$Strategy<? super K>, net.neoforged.neoforge.common.util.MutableHashedLinkedMap$MergeFunction<K, V>);
  public net.neoforged.neoforge.common.util.MutableHashedLinkedMap(it.unimi.dsi.fastutil.Hash$Strategy<? super K>, java.util.function.BiPredicate<K, V>);
  public net.neoforged.neoforge.common.util.MutableHashedLinkedMap(it.unimi.dsi.fastutil.Hash$Strategy<? super K>, net.neoforged.neoforge.common.util.MutableHashedLinkedMap$MergeFunction<K, V>, java.util.function.BiPredicate<K, V>);
  public V put(K, V);
  public boolean contains(K);
  public boolean isEmpty();
  public V remove(K);
  public V get(K);
  public java.util.Iterator<java.util.Map$Entry<K, V>> iterator();
  public V putFirst(K, V);
  public V putAfter(K, K, V);
  public V putBefore(K, K, V);
}
