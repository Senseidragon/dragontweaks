Compiled from "TriPredicate.java"
public interface net.neoforged.neoforge.common.util.TriPredicate<T, U, V> {
  public abstract boolean test(T, U, V);
  public default net.neoforged.neoforge.common.util.TriPredicate<T, U, V> and(net.neoforged.neoforge.common.util.TriPredicate<? super T, ? super U, ? super V>);
  public default net.neoforged.neoforge.common.util.TriPredicate<T, U, V> negate();
  public default net.neoforged.neoforge.common.util.TriPredicate<T, U, V> or(net.neoforged.neoforge.common.util.TriPredicate<? super T, ? super U, ? super V>);
}
