Compiled from "OptionalPredicate.java"
public interface com.minecolonies.api.util.OptionalPredicate<T> {
  public abstract java.util.Optional<java.lang.Boolean> test(T);
  public static <T> com.minecolonies.api.util.OptionalPredicate<T> empty();
  public static <T> com.minecolonies.api.util.OptionalPredicate<T> of(java.util.function.Predicate<T>);
  public static <T> com.minecolonies.api.util.OptionalPredicate<T> passIf(java.util.function.Predicate<T>);
  public static <T> com.minecolonies.api.util.OptionalPredicate<T> failIf(java.util.function.Predicate<T>);
  public default java.util.function.Predicate<T> orElse(boolean);
  public default com.minecolonies.api.util.OptionalPredicate<T> combine(com.minecolonies.api.util.OptionalPredicate<T>);
  public static <X> java.util.Optional<X> combine(java.util.Optional<X>, java.util.function.Supplier<java.util.Optional<X>>);
  public default com.minecolonies.api.util.OptionalPredicate<T> negate();
  public default com.minecolonies.api.util.OptionalPredicate<T> and(com.minecolonies.api.util.OptionalPredicate<T>);
  public default com.minecolonies.api.util.OptionalPredicate<T> or(com.minecolonies.api.util.OptionalPredicate<T>);
}
