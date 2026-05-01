Compiled from "IRequestHandler.java"
public interface com.minecolonies.api.colony.requestsystem.management.IRequestHandler {
  public abstract com.minecolonies.api.colony.requestsystem.manager.IRequestManager getManager();
  public abstract <Request extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable> com.minecolonies.api.colony.requestsystem.request.IRequest<Request> createRequest(com.minecolonies.api.colony.requestsystem.requester.IRequester, Request);
  public abstract void registerRequest(com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
  public abstract void assignRequest(com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> assignRequest(com.minecolonies.api.colony.requestsystem.request.IRequest<?>, java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>>);
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> assignRequestDefault(com.minecolonies.api.colony.requestsystem.request.IRequest<?>, java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>>);
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> reassignRequest(com.minecolonies.api.colony.requestsystem.request.IRequest<?>, java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>>);
  public abstract boolean isAssigned(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void onRequestResolved(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void onRequestCompleted(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void onRequestOverruled(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void onRequestCancelled(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void onChildRequestCancelled(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void onRequestCancelledDirectly(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void processDirectCancellationAndNotifyRequesterOf(com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
  public abstract void processDirectCancellationOf(com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
  public abstract void resolveRequest(com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
  public abstract void cleanRequestData(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract com.minecolonies.api.colony.requestsystem.request.IRequest<?> getRequest(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract com.minecolonies.api.colony.requestsystem.request.IRequest<?> getRequestOrNull(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract java.util.Collection<com.minecolonies.api.colony.requestsystem.request.IRequest<?>> getRequestsMadeByRequester(com.minecolonies.api.colony.requestsystem.requester.IRequester);
  public abstract void removeRequester(com.minecolonies.api.colony.requestsystem.requester.IRequester);
}
