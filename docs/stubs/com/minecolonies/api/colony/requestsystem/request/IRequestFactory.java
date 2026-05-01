Compiled from "IRequestFactory.java"
public interface com.minecolonies.api.colony.requestsystem.request.IRequestFactory<T extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable, R extends com.minecolonies.api.colony.requestsystem.request.IRequest<T>> extends com.minecolonies.api.colony.requestsystem.factory.IFactory<T, R> {
  public static final int NUMBER_OF_PROPERTIES;
  public default R getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, T, java.lang.Object...) throws java.lang.IllegalArgumentException;
  public default R getNewInstance(T, com.minecolonies.api.colony.requestsystem.requester.IRequester, com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract R getNewInstance(T, com.minecolonies.api.colony.requestsystem.requester.IRequester, com.minecolonies.api.colony.requestsystem.token.IToken<?>, com.minecolonies.api.colony.requestsystem.request.RequestState);
  public default java.lang.Object getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, java.lang.Object[]) throws java.lang.IllegalArgumentException;
}
