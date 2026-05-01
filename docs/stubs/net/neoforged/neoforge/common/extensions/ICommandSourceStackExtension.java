Compiled from "ICommandSourceStackExtension.java"
public interface net.neoforged.neoforge.common.extensions.ICommandSourceStackExtension {
  public default net.minecraft.world.scores.Scoreboard getScoreboard();
  public default net.minecraft.advancements.AdvancementHolder getAdvancement(net.minecraft.resources.ResourceLocation);
  public default net.minecraft.world.item.crafting.RecipeManager getRecipeManager();
  public default net.minecraft.world.level.Level getUnsidedLevel();
}
