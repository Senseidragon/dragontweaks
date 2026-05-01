Compiled from "RecipeStorage.java"
public class com.minecolonies.api.crafting.RecipeStorage implements com.minecolonies.api.crafting.IRecipeStorage {
  public static final net.minecraft.world.level.storage.loot.parameters.LootContextParamSet recipeLootParameters;
  public static com.minecolonies.api.crafting.RecipeStorage$Builder builder();
  public static com.minecolonies.api.crafting.RecipeStorage$Builder builder(com.minecolonies.api.crafting.IRecipeStorage);
  public java.util.List<com.minecolonies.api.crafting.ItemStorage> getInput();
  public java.util.List<com.minecolonies.api.crafting.ItemStorage> getCleanedInput();
  public net.minecraft.world.item.ItemStack getPrimaryOutput();
  public int getGridSize();
  public net.minecraft.world.level.block.Block getIntermediate();
  public boolean canFullFillRecipe(int, java.util.Map<com.minecolonies.api.crafting.ItemStorage, java.lang.Integer>, net.neoforged.neoforge.items.IItemHandler...);
  public boolean canFullFillRecipe(int, java.util.Map<com.minecolonies.api.crafting.ItemStorage, java.lang.Integer>, java.util.List<net.neoforged.neoforge.items.IItemHandler>, com.minecolonies.api.colony.buildings.IBuilding);
  public boolean equals(java.lang.Object);
  public int hashCode();
  public java.util.List<net.minecraft.world.item.ItemStack> fullfillRecipeAndCopy(net.minecraft.world.level.storage.loot.LootParams, java.util.List<net.neoforged.neoforge.items.IItemHandler>, boolean);
  public com.minecolonies.api.colony.requestsystem.token.IToken<?> getToken();
  public com.minecolonies.api.crafting.RecipeStorage getClassicForMultiOutput(net.minecraft.world.item.ItemStack);
  public com.minecolonies.api.crafting.RecipeStorage getClassicForMultiOutput(java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public com.minecolonies.api.crafting.AbstractRecipeType<com.minecolonies.api.crafting.IRecipeStorage> getRecipeType();
  public net.minecraft.resources.ResourceLocation getRecipeSource();
  public java.util.List<net.minecraft.world.item.ItemStack> getAlternateOutputs();
  public java.util.List<net.minecraft.world.item.ItemStack> getCraftingToolsAndSecondaryOutputs();
  public net.minecraft.resources.ResourceKey<net.minecraft.world.level.storage.loot.LootTable> getLootTable();
  public com.minecolonies.api.equipment.registry.EquipmentTypeEntry getRequiredTool();
  public java.util.List<net.minecraft.world.item.ItemStack> getCraftingTools();
  public java.util.List<net.minecraft.world.item.ItemStack> getSecondaryOutputs();
}
