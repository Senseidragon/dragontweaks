Compiled from "IRequestSystemDeliveryManJobDataStore.java"
public interface com.minecolonies.api.colony.requestsystem.data.IRequestSystemDeliveryManJobDataStore extends com.minecolonies.api.colony.requestsystem.data.IDataStore {
  public abstract java.util.LinkedList<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getQueue();
  public abstract java.util.Set<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getOngoingDeliveries();
}
