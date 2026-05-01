Compiled from "IRequester.java"
public interface com.minecolonies.api.colony.requestsystem.requester.IRequester {
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> getId();
  public abstract com.minecolonies.api.colony.requestsystem.location.ILocation getLocation();
  public abstract void onRequestedRequestComplete(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
  public abstract void onRequestedRequestCancelled(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
  public abstract net.minecraft.network.chat.MutableComponent getRequesterDisplayName(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
}
