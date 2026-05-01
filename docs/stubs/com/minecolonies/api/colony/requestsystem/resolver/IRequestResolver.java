Compiled from "IRequestResolver.java"
public interface com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<R extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable> extends com.minecolonies.api.colony.requestsystem.requester.IRequester {
  public abstract com.google.common.reflect.TypeToken<? extends R> getRequestType();
  public abstract boolean canResolveRequest(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.IRequest<? extends R>);
  public abstract java.util.List<com.minecolonies.api.colony.requestsystem.token.IToken<?>> attemptResolveRequest(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.IRequest<? extends R>);
  public abstract void resolveRequest(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.IRequest<? extends R>);
  public default void onRequestAssigned(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.IRequest<? extends R>, boolean);
  public abstract void onAssignedRequestBeingCancelled(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.IRequest<? extends R>);
  public abstract void onAssignedRequestCancelled(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.IRequest<? extends R>);
  public default void onColonyUpdate(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, java.util.function.Predicate<com.minecolonies.api.colony.requestsystem.request.IRequest<?>>);
  public default java.util.List<com.minecolonies.api.colony.requestsystem.request.IRequest<?>> getFollowupRequestForCompletion(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.IRequest<? extends R>);
  public default int getSuitabilityMetric(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.IRequest<? extends R>);
  public abstract int getPriority();
  public abstract boolean isValid();
}
