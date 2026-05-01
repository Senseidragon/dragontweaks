Compiled from "IQueuedRequestResolver.java"
public interface com.minecolonies.api.colony.requestsystem.resolver.IQueuedRequestResolver<R extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable> extends com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<R> {
  public abstract com.google.common.collect.ImmutableList<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getAllAssignedRequests();
  public abstract void onSystemReset();
}
