Compiled from "ICraftingBuildingModule.java"
public interface com.minecolonies.api.colony.buildings.modules.ICraftingBuildingModule extends com.minecolonies.api.colony.buildings.modules.IBuildingModule {
  public abstract com.minecolonies.api.colony.jobs.IJob<?> getCraftingJob();
  public abstract java.lang.String getId();
  public abstract java.lang.String getCustomRecipeKey();
  public default boolean canLearn(com.minecolonies.api.crafting.registry.CraftingType);
  public abstract java.util.Set<com.minecolonies.api.crafting.registry.CraftingType> getSupportedCraftingTypes();
  public abstract boolean isRecipeCompatible(com.minecolonies.api.crafting.IGenericRecipe);
  public abstract com.minecolonies.api.util.OptionalPredicate<net.minecraft.world.item.ItemStack> getIngredientValidator();
  public abstract boolean canLearnManyRecipes();
  public abstract boolean isVisible();
  public abstract java.util.List<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getRecipes();
  public abstract com.minecolonies.api.crafting.IRecipeStorage getFirstRecipe(net.minecraft.world.item.ItemStack);
  public abstract com.minecolonies.api.crafting.IRecipeStorage getFirstRecipe(java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public abstract com.minecolonies.api.crafting.IRecipeStorage getFirstFulfillableRecipe(java.util.function.Predicate<net.minecraft.world.item.ItemStack>, int, boolean);
  public abstract boolean fullFillRecipe(com.minecolonies.api.crafting.IRecipeStorage);
  public abstract net.minecraft.world.item.ItemStack getCraftingTool(com.minecolonies.api.entity.citizen.AbstractEntityCitizen);
  public abstract float getCraftingLuck(com.minecolonies.api.entity.citizen.AbstractEntityCitizen);
  public abstract void updateWorkerAvailableForRecipes();
  public abstract void replaceRecipe(com.minecolonies.api.colony.requestsystem.token.IToken<?>, com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract boolean canRecipeBeAdded(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void addRecipeToList(com.minecolonies.api.colony.requestsystem.token.IToken<?>, boolean);
  public abstract void switchOrder(int, int, boolean);
  public abstract java.util.List<com.minecolonies.api.crafting.IGenericRecipe> getAdditionalRecipesForDisplayPurposesOnly(net.minecraft.world.level.Level);
  public default java.util.List<net.minecraft.resources.ResourceKey<net.minecraft.world.level.storage.loot.LootTable>> getAdditionalLootTables();
  public abstract boolean addRecipe(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void removeRecipe(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void checkForWorkerSpecificRecipes();
  public abstract void clearRecipes();
  public abstract void improveRecipe(com.minecolonies.api.crafting.IRecipeStorage, int, com.minecolonies.api.colony.ICitizenData);
  public default net.minecraft.resources.ResourceLocation getUid();
  public static net.minecraft.resources.ResourceLocation getUid(com.minecolonies.api.colony.jobs.registry.JobEntry, java.lang.String);
  public abstract boolean holdsRecipe(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void toggle(int);
  public abstract boolean isDisabled(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
}
