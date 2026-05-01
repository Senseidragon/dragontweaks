Compiled from "DataMapEntry.java"
public final class net.neoforged.neoforge.registries.datamaps.DataMapEntry<T> extends java.lang.Record {
  public net.neoforged.neoforge.registries.datamaps.DataMapEntry(T, boolean);
  public static <T> com.mojang.serialization.Codec<net.neoforged.neoforge.registries.datamaps.DataMapEntry<T>> codec(net.neoforged.neoforge.registries.datamaps.DataMapType<?, T>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public T value();
  public boolean replace();
}
