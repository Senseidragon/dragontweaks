Compiled from "SizedIngredient.java"
public final class net.neoforged.neoforge.common.crafting.SizedIngredient {
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.common.crafting.SizedIngredient> FLAT_CODEC;
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.common.crafting.SizedIngredient> NESTED_CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, net.neoforged.neoforge.common.crafting.SizedIngredient> STREAM_CODEC;
  public static net.neoforged.neoforge.common.crafting.SizedIngredient of(net.minecraft.world.level.ItemLike, int);
  public static net.neoforged.neoforge.common.crafting.SizedIngredient of(net.minecraft.tags.TagKey<net.minecraft.world.item.Item>, int);
  public net.neoforged.neoforge.common.crafting.SizedIngredient(net.minecraft.world.item.crafting.Ingredient, int);
  public net.minecraft.world.item.crafting.Ingredient ingredient();
  public int count();
  public boolean test(net.minecraft.world.item.ItemStack);
  public net.minecraft.world.item.ItemStack[] getItems();
  public boolean equals(java.lang.Object);
  public int hashCode();
  public java.lang.String toString();
}
