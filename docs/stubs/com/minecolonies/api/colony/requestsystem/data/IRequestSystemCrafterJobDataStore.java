Compiled from "IRequestSystemCrafterJobDataStore.java"
public interface com.minecolonies.api.colony.requestsystem.data.IRequestSystemCrafterJobDataStore extends com.minecolonies.api.colony.requestsystem.data.IDataStore {
  public abstract java.util.LinkedList<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getQueue();
  public abstract java.util.List<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getAssignedTasks();
}
