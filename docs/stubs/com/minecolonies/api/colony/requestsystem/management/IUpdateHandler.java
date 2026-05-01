Compiled from "IUpdateHandler.java"
public interface com.minecolonies.api.colony.requestsystem.management.IUpdateHandler {
  public abstract com.minecolonies.api.colony.requestsystem.manager.IRequestManager getManager();
  public abstract void handleUpdate(com.minecolonies.api.colony.requestsystem.management.update.UpdateType);
  public abstract int getCurrentVersion();
}
