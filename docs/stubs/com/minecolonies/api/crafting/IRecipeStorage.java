Compiled from "IRecipeStorage.java"
public interface com.minecolonies.api.crafting.IRecipeStorage {
  public abstract java.util.List<com.minecolonies.api.crafting.ItemStorage> getInput();
  public abstract java.util.List<com.minecolonies.api.crafting.ItemStorage> getCleanedInput();
  public abstract net.minecraft.world.item.ItemStack getPrimaryOutput();
  public abstract int getGridSize();
  public abstract net.minecraft.world.level.block.Block getIntermediate();
  public abstract boolean canFullFillRecipe(int, java.util.Map<com.minecolonies.api.crafting.ItemStorage, java.lang.Integer>, net.neoforged.neoforge.items.IItemHandler...);
  public abstract boolean canFullFillRecipe(int, java.util.Map<com.minecolonies.api.crafting.ItemStorage, java.lang.Integer>, java.util.List<net.neoforged.neoforge.items.IItemHandler>, com.minecolonies.api.colony.buildings.IBuilding);
  public default boolean fullFillRecipe(net.minecraft.world.level.Level, net.neoforged.neoforge.items.IItemHandler...);
  public default boolean fullFillRecipe(net.minecraft.world.level.storage.loot.LootParams, net.neoforged.neoforge.items.IItemHandler...);
  public default boolean fullfillRecipe(net.minecraft.world.level.storage.loot.LootParams, java.util.List<net.neoforged.neoforge.items.IItemHandler>);
  public default boolean fullfillRecipe(net.minecraft.world.level.Level, java.util.List<net.neoforged.neoforge.items.IItemHandler>);
  public abstract java.util.List<net.minecraft.world.item.ItemStack> fullfillRecipeAndCopy(net.minecraft.world.level.storage.loot.LootParams, java.util.List<net.neoforged.neoforge.items.IItemHandler>, boolean);
  public default java.util.List<net.minecraft.world.item.ItemStack> fullfillRecipeAndCopy(net.minecraft.world.level.Level, java.util.List<net.neoforged.neoforge.items.IItemHandler>, boolean);
  public abstract com.minecolonies.api.crafting.AbstractRecipeType<com.minecolonies.api.crafting.IRecipeStorage> getRecipeType();
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getAlternateOutputs();
  public abstract com.minecolonies.api.crafting.RecipeStorage getClassicForMultiOutput(net.minecraft.world.item.ItemStack);
  public abstract com.minecolonies.api.crafting.RecipeStorage getClassicForMultiOutput(java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public abstract net.minecraft.resources.ResourceLocation getRecipeSource();
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getSecondaryOutputs();
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getCraftingToolsAndSecondaryOutputs();
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getCraftingTools();
  public abstract com.minecolonies.api.equipment.registry.EquipmentTypeEntry getRequiredTool();
  public abstract net.minecraft.resources.ResourceKey<net.minecraft.world.level.storage.loot.LootTable> getLootTable();
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> getToken();
}
