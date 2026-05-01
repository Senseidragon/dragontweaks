Compiled from "IRequestResolverProvider.java"
public interface com.minecolonies.api.colony.requestsystem.resolver.IRequestResolverProvider {
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> getId();
  public abstract com.google.common.collect.ImmutableCollection<com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?>> getResolvers();
}
