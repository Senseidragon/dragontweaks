Compiled from "IRecipeOutputExtension.java"
public interface net.neoforged.neoforge.common.extensions.IRecipeOutputExtension {
  public abstract void accept(net.minecraft.resources.ResourceLocation, net.minecraft.world.item.crafting.Recipe<?>, net.minecraft.advancements.AdvancementHolder, net.neoforged.neoforge.common.conditions.ICondition...);
  public default net.minecraft.data.recipes.RecipeOutput withConditions(net.neoforged.neoforge.common.conditions.ICondition...);
}
