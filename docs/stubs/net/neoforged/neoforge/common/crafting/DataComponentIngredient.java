Compiled from "DataComponentIngredient.java"
public class net.neoforged.neoforge.common.crafting.DataComponentIngredient implements net.neoforged.neoforge.common.crafting.ICustomIngredient {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.crafting.DataComponentIngredient> CODEC;
  public net.neoforged.neoforge.common.crafting.DataComponentIngredient(net.minecraft.core.HolderSet<net.minecraft.world.item.Item>, net.minecraft.core.component.DataComponentPredicate, boolean);
  public boolean test(net.minecraft.world.item.ItemStack);
  public java.util.stream.Stream<net.minecraft.world.item.ItemStack> getItems();
  public boolean isSimple();
  public net.neoforged.neoforge.common.crafting.IngredientType<?> getType();
  public net.minecraft.core.HolderSet<net.minecraft.world.item.Item> items();
  public net.minecraft.core.component.DataComponentPredicate components();
  public boolean isStrict();
  public static net.minecraft.world.item.crafting.Ingredient of(boolean, net.minecraft.world.item.ItemStack);
  public static <T> net.minecraft.world.item.crafting.Ingredient of(boolean, net.minecraft.core.component.DataComponentType<? super T>, T, net.minecraft.world.level.ItemLike...);
  public static <T> net.minecraft.world.item.crafting.Ingredient of(boolean, java.util.function.Supplier<? extends net.minecraft.core.component.DataComponentType<? super T>>, T, net.minecraft.world.level.ItemLike...);
  public static net.minecraft.world.item.crafting.Ingredient of(boolean, net.minecraft.core.component.DataComponentMap, net.minecraft.world.level.ItemLike...);
  public static net.minecraft.world.item.crafting.Ingredient of(boolean, net.minecraft.core.component.DataComponentMap, net.minecraft.core.Holder<net.minecraft.world.item.Item>...);
  public static net.minecraft.world.item.crafting.Ingredient of(boolean, net.minecraft.core.component.DataComponentMap, net.minecraft.core.HolderSet<net.minecraft.world.item.Item>);
  public static net.minecraft.world.item.crafting.Ingredient of(boolean, net.minecraft.core.component.DataComponentPredicate, net.minecraft.core.Holder<net.minecraft.world.item.Item>...);
  public static net.minecraft.world.item.crafting.Ingredient of(boolean, net.minecraft.core.component.DataComponentPredicate, net.minecraft.world.level.ItemLike...);
  public static net.minecraft.world.item.crafting.Ingredient of(boolean, net.minecraft.core.component.DataComponentPredicate, net.minecraft.core.HolderSet<net.minecraft.world.item.Item>);
}
