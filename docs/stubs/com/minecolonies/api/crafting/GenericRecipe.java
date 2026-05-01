Compiled from "GenericRecipe.java"
public class com.minecolonies.api.crafting.GenericRecipe implements com.minecolonies.api.crafting.IGenericRecipe {
  public static com.minecolonies.api.crafting.GenericRecipe$Builder builder();
  public static com.minecolonies.api.crafting.GenericRecipe$Builder builder(com.minecolonies.api.crafting.IGenericRecipe);
  public static com.minecolonies.api.crafting.GenericRecipe$Builder builder(com.minecolonies.api.crafting.IRecipeStorage);
  public static com.minecolonies.api.crafting.IGenericRecipe of(net.minecraft.world.item.crafting.RecipeHolder<?>, net.minecraft.world.level.Level);
  public static com.minecolonies.api.crafting.IGenericRecipe of(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public int getGridSize();
  public net.minecraft.resources.ResourceLocation getRecipeId();
  public net.minecraft.world.item.ItemStack getPrimaryOutput();
  public java.util.List<net.minecraft.world.item.ItemStack> getAllMultiOutputs();
  public java.util.List<net.minecraft.world.item.ItemStack> getAdditionalOutputs();
  public java.util.List<java.util.List<net.minecraft.world.item.ItemStack>> getInputs();
  public java.util.function.Supplier<java.util.List<net.minecraft.network.chat.Component>> getRestrictions();
  public int getLevelSort();
  public java.util.Optional<java.lang.Boolean> matchesOutput(com.minecolonies.api.util.OptionalPredicate<net.minecraft.world.item.ItemStack>);
  public java.util.Optional<java.lang.Boolean> matchesInput(com.minecolonies.api.util.OptionalPredicate<net.minecraft.world.item.ItemStack>);
  public net.minecraft.world.level.block.Block getIntermediate();
  public net.minecraft.resources.ResourceKey<net.minecraft.world.level.storage.loot.LootTable> getLootTable();
  public com.minecolonies.api.equipment.registry.EquipmentTypeEntry getRequiredTool();
  public net.minecraft.world.entity.EntityType<?> getRequiredEntity();
  public java.lang.String toString();
}
