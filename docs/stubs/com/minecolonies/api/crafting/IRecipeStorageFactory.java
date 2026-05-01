Compiled from "IRecipeStorageFactory.java"
public interface com.minecolonies.api.crafting.IRecipeStorageFactory extends com.minecolonies.api.colony.requestsystem.factory.IFactory<com.minecolonies.api.colony.requestsystem.token.IToken<?>, com.minecolonies.api.crafting.RecipeStorage> {
  public default com.minecolonies.api.crafting.RecipeStorage getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.token.IToken<?>, java.lang.Object...);
  public default java.lang.Object getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, java.lang.Object[]) throws java.lang.IllegalArgumentException;
}
