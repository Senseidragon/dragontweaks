Compiled from "AbstractRecipeType.java"
public abstract class com.minecolonies.api.crafting.AbstractRecipeType<R extends com.minecolonies.api.crafting.IRecipeStorage> {
  public com.minecolonies.api.crafting.AbstractRecipeType(R);
  public com.minecolonies.api.crafting.IRecipeStorage getRecipe();
  public abstract net.minecraft.resources.ResourceLocation getId();
  public java.util.List<net.minecraft.world.item.ItemStack> getOutputDisplayStacks();
}
