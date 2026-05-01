Compiled from "IResearchEffectFactory.java"
public interface com.minecolonies.api.research.factories.IResearchEffectFactory<T extends com.minecolonies.api.research.IResearchEffect> extends com.minecolonies.api.colony.requestsystem.factory.IFactory<com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, T> {
  public default T getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, java.lang.Object...);
  public abstract T getNewInstance(java.lang.String, java.lang.Object);
  public default java.lang.Object getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, java.lang.Object[]) throws java.lang.IllegalArgumentException;
}
