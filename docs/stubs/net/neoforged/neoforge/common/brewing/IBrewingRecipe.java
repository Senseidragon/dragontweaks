Compiled from "IBrewingRecipe.java"
public interface net.neoforged.neoforge.common.brewing.IBrewingRecipe {
  public abstract boolean isInput(net.minecraft.world.item.ItemStack);
  public abstract boolean isIngredient(net.minecraft.world.item.ItemStack);
  public abstract net.minecraft.world.item.ItemStack getOutput(net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack);
}
