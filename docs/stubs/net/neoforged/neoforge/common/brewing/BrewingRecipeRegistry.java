Compiled from "BrewingRecipeRegistry.java"
public final class net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry extends java.lang.Record {
  public net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry(java.util.List<net.neoforged.neoforge.common.brewing.IBrewingRecipe>);
  public net.minecraft.world.item.ItemStack getOutput(net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack);
  public boolean hasOutput(net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack);
  public boolean isValidIngredient(net.minecraft.world.item.ItemStack);
  public boolean isValidInput(net.minecraft.world.item.ItemStack);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.List<net.neoforged.neoforge.common.brewing.IBrewingRecipe> recipes();
}
