Compiled from "IRetryingRequestResolver.java"
public interface com.minecolonies.api.colony.requestsystem.resolver.retrying.IRetryingRequestResolver extends com.minecolonies.api.colony.requestsystem.resolver.IQueuedRequestResolver<com.minecolonies.api.colony.requestsystem.requestable.IRetryable>, com.minecolonies.api.tileentities.ITickable {
  public abstract void updateManager(com.minecolonies.api.colony.requestsystem.manager.IRequestManager);
  public abstract int getMaximalTries();
  public abstract int getMaximalDelayBetweenRetriesInTicks();
  public abstract int getCurrentReassignmentAttempt();
  public default boolean isReassigning();
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> getCurrentlyBeingReassignedRequest();
}
