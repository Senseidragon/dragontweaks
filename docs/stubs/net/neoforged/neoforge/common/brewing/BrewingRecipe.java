Compiled from "BrewingRecipe.java"
public class net.neoforged.neoforge.common.brewing.BrewingRecipe implements net.neoforged.neoforge.common.brewing.IBrewingRecipe {
  public net.neoforged.neoforge.common.brewing.BrewingRecipe(net.minecraft.world.item.crafting.Ingredient, net.minecraft.world.item.crafting.Ingredient, net.minecraft.world.item.ItemStack);
  public boolean isInput(net.minecraft.world.item.ItemStack);
  public net.minecraft.world.item.ItemStack getOutput(net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack);
  public net.minecraft.world.item.crafting.Ingredient getInput();
  public net.minecraft.world.item.crafting.Ingredient getIngredient();
  public net.minecraft.world.item.ItemStack getOutput();
  public boolean isIngredient(net.minecraft.world.item.ItemStack);
}
