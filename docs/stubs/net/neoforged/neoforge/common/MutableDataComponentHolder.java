Compiled from "MutableDataComponentHolder.java"
public interface net.neoforged.neoforge.common.MutableDataComponentHolder extends net.minecraft.core.component.DataComponentHolder {
  public abstract <T> T set(net.minecraft.core.component.DataComponentType<? super T>, T);
  public default <T> T set(java.util.function.Supplier<? extends net.minecraft.core.component.DataComponentType<? super T>>, T);
  public default <T, U> T update(net.minecraft.core.component.DataComponentType<T>, T, U, java.util.function.BiFunction<T, U, T>);
  public default <T, U> T update(java.util.function.Supplier<? extends net.minecraft.core.component.DataComponentType<T>>, T, U, java.util.function.BiFunction<T, U, T>);
  public default <T> T update(net.minecraft.core.component.DataComponentType<T>, T, java.util.function.UnaryOperator<T>);
  public default <T> T update(java.util.function.Supplier<? extends net.minecraft.core.component.DataComponentType<T>>, T, java.util.function.UnaryOperator<T>);
  public abstract <T> T remove(net.minecraft.core.component.DataComponentType<? extends T>);
  public default <T> T remove(java.util.function.Supplier<? extends net.minecraft.core.component.DataComponentType<? extends T>>);
  public default void copyFrom(net.minecraft.core.component.DataComponentHolder, net.minecraft.core.component.DataComponentType<?>...);
  public default void copyFrom(net.minecraft.core.component.DataComponentHolder, java.util.function.Supplier<? extends net.minecraft.core.component.DataComponentType<?>>...);
  public abstract void applyComponents(net.minecraft.core.component.DataComponentPatch);
  public abstract void applyComponents(net.minecraft.core.component.DataComponentMap);
}
