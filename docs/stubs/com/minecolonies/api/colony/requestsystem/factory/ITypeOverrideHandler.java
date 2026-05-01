Compiled from "ITypeOverrideHandler.java"
public interface com.minecolonies.api.colony.requestsystem.factory.ITypeOverrideHandler<O> {
  public abstract boolean matches(com.google.common.reflect.TypeToken<?>);
  public abstract com.google.common.reflect.TypeToken<O> getOutputType();
}
