Compiled from "IDataStoreManager.java"
public interface com.minecolonies.api.colony.requestsystem.data.IDataStoreManager {
  public abstract <T extends com.minecolonies.api.colony.requestsystem.data.IDataStore> T get(com.minecolonies.api.colony.requestsystem.token.IToken<?>, com.google.common.reflect.TypeToken<T>);
  public abstract <T extends com.minecolonies.api.colony.requestsystem.data.IDataStore> T get(com.minecolonies.api.colony.requestsystem.token.IToken<?>, java.util.function.Supplier<T>);
  public abstract void remove(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void removeAll();
}
