Compiled from "IIdentitiesDataStore.java"
public interface com.minecolonies.api.colony.requestsystem.data.IIdentitiesDataStore<K, V> extends com.minecolonies.api.colony.requestsystem.data.IDataStore {
  public abstract com.google.common.collect.BiMap<K, V> getIdentities();
}
