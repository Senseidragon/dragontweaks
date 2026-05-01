Compiled from "RecipeBookManager.java"
public final class net.neoforged.neoforge.client.RecipeBookManager {
  public net.neoforged.neoforge.client.RecipeBookManager();
  public static <T extends net.minecraft.world.item.crafting.Recipe<?>> net.minecraft.client.RecipeBookCategories findCategories(net.minecraft.world.item.crafting.RecipeType<T>, net.minecraft.world.item.crafting.RecipeHolder<T>);
  public static java.util.Map<net.minecraft.client.RecipeBookCategories, java.util.List<net.minecraft.client.RecipeBookCategories>> getAggregateCategories();
  public static java.util.List<net.minecraft.client.RecipeBookCategories> getCustomCategoriesOrEmpty(net.minecraft.world.inventory.RecipeBookType);
  public static void init();
}
