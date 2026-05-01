Compiled from "CraftingUtils.java"
public final class com.minecolonies.api.util.CraftingUtils {
  public static int calculateMaxCraftingCount(net.minecraft.world.item.ItemStack, com.minecolonies.api.crafting.IRecipeStorage);
  public static int calculateMaxCraftingCount(int, com.minecolonies.api.crafting.IRecipeStorage);
  public static com.minecolonies.api.util.OptionalPredicate<net.minecraft.world.item.ItemStack> getProductValidatorBasedOnTags(java.lang.String);
  public static com.minecolonies.api.util.OptionalPredicate<net.minecraft.world.item.ItemStack> getIngredientValidatorBasedOnTags(java.lang.String);
  public static com.minecolonies.api.util.OptionalPredicate<net.minecraft.world.item.ItemStack> getIngredientValidatorBasedOnTags(java.lang.String, boolean);
  public static java.util.Optional<java.lang.Boolean> isRecipeCompatibleBasedOnTags(com.minecolonies.api.crafting.IGenericRecipe, java.lang.String);
  public static void forEachCreativeTabItems(net.minecraft.world.item.CreativeModeTab$ItemDisplayParameters, java.util.function.BiConsumer<net.minecraft.world.item.CreativeModeTab, java.util.Collection<net.minecraft.world.item.ItemStack>>);
}
