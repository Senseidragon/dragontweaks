Compiled from "CompostRecipe.java"
public class com.minecolonies.api.crafting.CompostRecipe implements net.minecraft.world.item.crafting.Recipe<net.minecraft.world.item.crafting.SingleRecipeInput> {
  public static final com.mojang.serialization.MapCodec<com.minecolonies.api.crafting.CompostRecipe> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.crafting.CompostRecipe> STREAM_CODEC;
  public com.minecolonies.api.crafting.CompostRecipe(net.minecraft.world.item.crafting.Ingredient, int);
  public net.minecraft.world.item.crafting.RecipeType<?> getType();
  public net.minecraft.world.item.crafting.Ingredient getInput();
  public int getStrength();
  public int getFermentTime();
  public boolean matches(net.minecraft.world.item.crafting.SingleRecipeInput, net.minecraft.world.level.Level);
  public net.minecraft.world.item.ItemStack assemble(net.minecraft.world.item.crafting.SingleRecipeInput, net.minecraft.core.HolderLookup$Provider);
  public boolean canCraftInDimensions(int, int);
  public net.minecraft.world.item.ItemStack getResultItem(net.minecraft.core.HolderLookup$Provider);
  public net.minecraft.world.item.crafting.RecipeSerializer<?> getSerializer();
  public net.minecraft.core.NonNullList<net.minecraft.world.item.crafting.Ingredient> getIngredients();
  public static com.minecolonies.api.crafting.CompostRecipe individualize(net.minecraft.world.item.Item, net.minecraft.world.item.crafting.RecipeHolder<com.minecolonies.api.crafting.CompostRecipe>);
  public net.minecraft.world.item.ItemStack assemble(net.minecraft.world.item.crafting.RecipeInput, net.minecraft.core.HolderLookup$Provider);
  public boolean matches(net.minecraft.world.item.crafting.RecipeInput, net.minecraft.world.level.Level);
}
