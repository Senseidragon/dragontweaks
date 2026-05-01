Compiled from "IProviderHandler.java"
public interface com.minecolonies.api.colony.requestsystem.management.IProviderHandler {
  public abstract com.minecolonies.api.colony.requestsystem.manager.IRequestManager getManager();
  public abstract java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getRegisteredResolvers(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolverProvider);
  public abstract void registerProvider(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolverProvider);
  public abstract void removeProvider(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getRegisteredResolvers(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void removeProvider(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolverProvider);
}
