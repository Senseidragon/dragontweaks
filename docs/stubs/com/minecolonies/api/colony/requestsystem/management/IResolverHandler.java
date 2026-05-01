Compiled from "IResolverHandler.java"
public interface com.minecolonies.api.colony.requestsystem.management.IResolverHandler {
  public abstract com.minecolonies.api.colony.requestsystem.manager.IRequestManager getManager();
  public abstract java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>> registerResolvers(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?>...);
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> registerResolver(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<? extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable>);
  public abstract java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>> registerResolvers(java.util.Collection<com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?>>);
  public abstract void removeResolver(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void removeResolver(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?>);
  public abstract java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getRequestsAssignedToResolver(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?>);
  public abstract com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<? extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable> getResolver(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void removeResolverInternal(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?>);
  public abstract void removeResolvers(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?>...);
  public abstract void removeResolvers(java.lang.Iterable<com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?>>);
  public abstract void addRequestToResolver(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?>, com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
  public abstract void removeRequestFromResolver(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?>, com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
  public abstract com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<? extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable> getResolverForRequest(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<? extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable> getResolverForRequest(com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
  public abstract void onColonyUpdate(java.util.function.Predicate<com.minecolonies.api.colony.requestsystem.request.IRequest<?>>);
  public abstract boolean isBeingRemoved(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void processResolverForRemoval(java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>>, com.minecolonies.api.colony.requestsystem.token.IToken<?>);
}
