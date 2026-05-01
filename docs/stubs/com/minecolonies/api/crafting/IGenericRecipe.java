Compiled from "IGenericRecipe.java"
public interface com.minecolonies.api.crafting.IGenericRecipe {
  public abstract int getGridSize();
  public abstract net.minecraft.resources.ResourceLocation getRecipeId();
  public abstract net.minecraft.world.item.ItemStack getPrimaryOutput();
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getAllMultiOutputs();
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getAdditionalOutputs();
  public abstract java.util.List<java.util.List<net.minecraft.world.item.ItemStack>> getInputs();
  public abstract java.util.Optional<java.lang.Boolean> matchesOutput(com.minecolonies.api.util.OptionalPredicate<net.minecraft.world.item.ItemStack>);
  public abstract java.util.Optional<java.lang.Boolean> matchesInput(com.minecolonies.api.util.OptionalPredicate<net.minecraft.world.item.ItemStack>);
  public abstract net.minecraft.world.level.block.Block getIntermediate();
  public abstract net.minecraft.resources.ResourceKey<net.minecraft.world.level.storage.loot.LootTable> getLootTable();
  public abstract com.minecolonies.api.equipment.registry.EquipmentTypeEntry getRequiredTool();
  public abstract net.minecraft.world.entity.EntityType<?> getRequiredEntity();
  public abstract java.util.function.Supplier<java.util.List<net.minecraft.network.chat.Component>> getRestrictions();
  public abstract int getLevelSort();
}
