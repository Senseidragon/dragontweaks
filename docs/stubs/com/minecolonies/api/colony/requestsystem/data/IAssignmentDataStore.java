Compiled from "IAssignmentDataStore.java"
public interface com.minecolonies.api.colony.requestsystem.data.IAssignmentDataStore<K, V> extends com.minecolonies.api.colony.requestsystem.data.IDataStore {
  public abstract java.util.Map<K, java.util.Collection<V>> getAssignments();
  public default K getAssignmentForValue(V);
}
