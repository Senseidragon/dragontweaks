Compiled from "IFurnaceRecipes.java"
public interface com.minecolonies.api.compatibility.IFurnaceRecipes {
  public abstract net.minecraft.world.item.ItemStack getSmeltingResult(net.minecraft.world.item.ItemStack);
  public abstract com.minecolonies.api.crafting.IRecipeStorage getFirstSmeltingRecipeByResult(com.minecolonies.api.crafting.ItemStorage);
}
