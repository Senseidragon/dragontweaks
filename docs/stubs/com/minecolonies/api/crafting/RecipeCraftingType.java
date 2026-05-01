Compiled from "RecipeCraftingType.java"
public class com.minecolonies.api.crafting.RecipeCraftingType<C extends net.minecraft.world.item.crafting.RecipeInput, T extends net.minecraft.world.item.crafting.Recipe<C>> extends com.minecolonies.api.crafting.registry.CraftingType {
  public com.minecolonies.api.crafting.RecipeCraftingType(net.minecraft.resources.ResourceLocation, net.minecraft.world.item.crafting.RecipeType<T>, java.util.function.Predicate<net.minecraft.world.item.crafting.RecipeHolder<T>>);
  public java.util.List<com.minecolonies.api.crafting.IGenericRecipe> findRecipes(net.minecraft.world.item.crafting.RecipeManager, net.minecraft.world.level.Level);
}
