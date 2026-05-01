Compiled from "IRecipeManager.java"
public interface com.minecolonies.api.crafting.IRecipeManager {
  public abstract com.google.common.collect.ImmutableMap<com.minecolonies.api.colony.requestsystem.token.IToken<?>, com.minecolonies.api.crafting.IRecipeStorage> getRecipes();
  public abstract com.minecolonies.api.crafting.IRecipeStorage getRecipe(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> addRecipe(com.minecolonies.api.crafting.IRecipeStorage);
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> checkOrAddRecipe(com.minecolonies.api.crafting.IRecipeStorage);
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> getRecipeId(com.minecolonies.api.crafting.IRecipeStorage);
  public abstract void registerUse(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void reset();
}
