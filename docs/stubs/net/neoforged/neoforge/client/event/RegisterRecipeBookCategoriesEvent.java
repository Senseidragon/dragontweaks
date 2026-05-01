Compiled from "RegisterRecipeBookCategoriesEvent.java"
public class net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent(java.util.Map<net.minecraft.client.RecipeBookCategories, com.google.common.collect.ImmutableList<net.minecraft.client.RecipeBookCategories>>, java.util.Map<net.minecraft.world.inventory.RecipeBookType, com.google.common.collect.ImmutableList<net.minecraft.client.RecipeBookCategories>>, java.util.Map<net.minecraft.world.item.crafting.RecipeType<?>, java.util.function.Function<net.minecraft.world.item.crafting.RecipeHolder<?>, net.minecraft.client.RecipeBookCategories>>);
  public void registerAggregateCategory(net.minecraft.client.RecipeBookCategories, java.util.List<net.minecraft.client.RecipeBookCategories>);
  public void registerBookCategories(net.minecraft.world.inventory.RecipeBookType, java.util.List<net.minecraft.client.RecipeBookCategories>);
  public void registerRecipeCategoryFinder(net.minecraft.world.item.crafting.RecipeType<?>, java.util.function.Function<net.minecraft.world.item.crafting.RecipeHolder<?>, net.minecraft.client.RecipeBookCategories>);
}
