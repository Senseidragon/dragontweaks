Compiled from "ICustomIngredient.java"
public interface net.neoforged.neoforge.common.crafting.ICustomIngredient {
  public abstract boolean test(net.minecraft.world.item.ItemStack);
  public abstract java.util.stream.Stream<net.minecraft.world.item.ItemStack> getItems();
  public abstract boolean isSimple();
  public abstract net.neoforged.neoforge.common.crafting.IngredientType<?> getType();
  public default net.minecraft.world.item.crafting.Ingredient toVanilla();
}
