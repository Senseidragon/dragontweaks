Compiled from "ITokenHandler.java"
public interface com.minecolonies.api.colony.requestsystem.management.ITokenHandler {
  public abstract com.minecolonies.api.colony.requestsystem.manager.IRequestManager getManager();
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> generateNewToken();
}
