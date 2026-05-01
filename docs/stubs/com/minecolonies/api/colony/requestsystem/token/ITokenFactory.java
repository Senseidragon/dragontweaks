Compiled from "ITokenFactory.java"
public interface com.minecolonies.api.colony.requestsystem.token.ITokenFactory<T, RT extends com.minecolonies.api.colony.requestsystem.token.IToken<?>> extends com.minecolonies.api.colony.requestsystem.factory.IFactory<T, RT> {
  public default RT getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, T, java.lang.Object...) throws java.lang.IllegalArgumentException;
  public abstract RT getNewInstance(T);
  public default java.lang.Object getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, java.lang.Object[]) throws java.lang.IllegalArgumentException;
}
