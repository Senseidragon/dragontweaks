Compiled from "ILocationFactory.java"
public interface com.minecolonies.api.colony.requestsystem.location.ILocationFactory<T, L extends com.minecolonies.api.colony.requestsystem.location.ILocation> extends com.minecolonies.api.colony.requestsystem.factory.IFactory<T, L> {
  public default L getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, T, java.lang.Object...) throws java.lang.IllegalArgumentException;
  public abstract L getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, T);
  public default java.lang.Object getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, java.lang.Object[]) throws java.lang.IllegalArgumentException;
}
