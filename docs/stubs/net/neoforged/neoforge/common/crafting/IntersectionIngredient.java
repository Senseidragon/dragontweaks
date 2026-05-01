Compiled from "IntersectionIngredient.java"
public final class net.neoforged.neoforge.common.crafting.IntersectionIngredient extends java.lang.Record implements net.neoforged.neoforge.common.crafting.ICustomIngredient {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.crafting.IntersectionIngredient> CODEC;
  public net.neoforged.neoforge.common.crafting.IntersectionIngredient(java.util.List<net.minecraft.world.item.crafting.Ingredient>);
  public static net.minecraft.world.item.crafting.Ingredient of(net.minecraft.world.item.crafting.Ingredient...);
  public boolean test(net.minecraft.world.item.ItemStack);
  public java.util.stream.Stream<net.minecraft.world.item.ItemStack> getItems();
  public boolean isSimple();
  public net.neoforged.neoforge.common.crafting.IngredientType<?> getType();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.List<net.minecraft.world.item.crafting.Ingredient> children();
}
