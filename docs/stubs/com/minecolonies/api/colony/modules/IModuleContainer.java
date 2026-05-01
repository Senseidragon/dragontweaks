Compiled from "IModuleContainer.java"
public interface com.minecolonies.api.colony.modules.IModuleContainer<T> {
  public abstract java.util.List<T> getModules();
  public abstract java.lang.Class<T> getClassType();
  public default <T2 extends T> java.util.List<T2> getModules(java.lang.Class<T2>);
  public default java.util.List<T> getModules(java.util.function.Predicate<T>);
  public default <T2 extends T> java.util.List<T2> getModules(java.lang.Class<T2>, java.util.function.Predicate<T2>);
  public default <T2 extends T> T2 getModule(java.lang.Class<T2>);
  public default T getModule(java.util.function.Predicate<T>);
  public default <T2 extends T> T2 getModule(java.lang.Class<T2>, java.util.function.Predicate<T2>);
  public default <T2 extends T> boolean hasModule(java.lang.Class<T2>);
  public default boolean hasModule(java.util.function.Predicate<T>);
  public default <T2 extends T> boolean hasModule(java.lang.Class<T2>, java.util.function.Predicate<T2>);
  public default <T2 extends T> T2 getFirstModuleOccurance(java.lang.Class<T2>);
  public default <T2 extends T> T2 getModuleMatching(java.lang.Class<T2>, java.util.function.Predicate<T2>);
  public default <T2 extends T> java.util.List<T2> getModulesByType(java.lang.Class<T2>);
}
