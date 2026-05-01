Compiled from "PermissionDynamicContextKey.java"
public final class net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContextKey<T> extends java.lang.Record {
  public net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContextKey(java.lang.Class<T>, java.lang.String, java.util.function.Function<T, java.lang.String>);
  public net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContext<T> createContext(T);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.lang.Class<T> typeToken();
  public java.lang.String name();
  public java.util.function.Function<T, java.lang.String> serializer();
}
