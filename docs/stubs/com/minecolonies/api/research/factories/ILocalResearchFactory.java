Compiled from "ILocalResearchFactory.java"
public interface com.minecolonies.api.research.factories.ILocalResearchFactory extends com.minecolonies.api.colony.requestsystem.factory.IFactory<com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, com.minecolonies.api.research.ILocalResearch> {
  public default com.minecolonies.api.research.ILocalResearch getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, java.lang.Object...);
  public abstract com.minecolonies.api.research.ILocalResearch getNewInstance(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation, int);
  public default java.lang.Object getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, java.lang.Object[]) throws java.lang.IllegalArgumentException;
}
