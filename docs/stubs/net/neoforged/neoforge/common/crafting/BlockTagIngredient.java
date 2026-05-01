Compiled from "BlockTagIngredient.java"
public class net.neoforged.neoforge.common.crafting.BlockTagIngredient implements net.neoforged.neoforge.common.crafting.ICustomIngredient {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.crafting.BlockTagIngredient> CODEC;
  public net.neoforged.neoforge.common.crafting.BlockTagIngredient(net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block>);
  public java.util.stream.Stream<net.minecraft.world.item.ItemStack> getItems();
  public boolean test(net.minecraft.world.item.ItemStack);
  public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getTag();
  public boolean isSimple();
  public net.neoforged.neoforge.common.crafting.IngredientType<?> getType();
  public boolean equals(java.lang.Object);
  public int hashCode();
}
