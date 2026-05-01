Compiled from "Lazy.java"
public final class net.neoforged.neoforge.common.util.Lazy<T> implements java.util.function.Supplier<T> {
  public static <T> net.neoforged.neoforge.common.util.Lazy<T> of(java.util.function.Supplier<T>);
  public synchronized void invalidate();
  public T get();
}
