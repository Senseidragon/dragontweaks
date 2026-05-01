Compiled from "CountedIngredient.java"
public final class com.minecolonies.api.crafting.CountedIngredient extends java.lang.Record implements net.neoforged.neoforge.common.crafting.ICustomIngredient {
  public static final com.mojang.serialization.MapCodec<com.minecolonies.api.crafting.CountedIngredient> CODEC;
  public com.minecolonies.api.crafting.CountedIngredient(net.minecraft.world.item.crafting.Ingredient, int);
  public static net.minecraft.world.item.crafting.Ingredient of(net.minecraft.world.item.crafting.Ingredient, int);
  public boolean test(net.minecraft.world.item.ItemStack);
  public boolean isSimple();
  public net.neoforged.neoforge.common.crafting.IngredientType<?> getType();
  public java.util.stream.Stream<net.minecraft.world.item.ItemStack> getItems();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.world.item.crafting.Ingredient child();
  public int count();
}
