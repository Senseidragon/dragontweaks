Compiled from "DifferenceIngredient.java"
public final class net.neoforged.neoforge.common.crafting.DifferenceIngredient extends java.lang.Record implements net.neoforged.neoforge.common.crafting.ICustomIngredient {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.crafting.DifferenceIngredient> CODEC;
  public net.neoforged.neoforge.common.crafting.DifferenceIngredient(net.minecraft.world.item.crafting.Ingredient, net.minecraft.world.item.crafting.Ingredient);
  public java.util.stream.Stream<net.minecraft.world.item.ItemStack> getItems();
  public boolean test(net.minecraft.world.item.ItemStack);
  public boolean isSimple();
  public net.neoforged.neoforge.common.crafting.IngredientType<?> getType();
  public static net.minecraft.world.item.crafting.Ingredient of(net.minecraft.world.item.crafting.Ingredient, net.minecraft.world.item.crafting.Ingredient);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.world.item.crafting.Ingredient base();
  public net.minecraft.world.item.crafting.Ingredient subtracted();
}
