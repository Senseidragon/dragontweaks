Compiled from "IGlobalResearchFactory.java"
public interface com.minecolonies.api.research.factories.IGlobalResearchFactory extends com.minecolonies.api.colony.requestsystem.factory.IFactory<com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, com.minecolonies.api.research.IGlobalResearch> {
  public default com.minecolonies.api.research.IGlobalResearch getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, java.lang.Object...);
  public abstract com.minecolonies.api.research.IGlobalResearch getNewInstance(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation, net.minecraft.network.chat.contents.TranslatableContents, net.minecraft.network.chat.contents.TranslatableContents, int, int, boolean, boolean, boolean, boolean, boolean);
  public default java.lang.Object getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, java.lang.Object[]) throws java.lang.IllegalArgumentException;
}
