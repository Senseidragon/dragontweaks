Compiled from "IngredientType.java"
public final class net.neoforged.neoforge.common.crafting.IngredientType<T extends net.neoforged.neoforge.common.crafting.ICustomIngredient> extends java.lang.Record {
  public net.neoforged.neoforge.common.crafting.IngredientType(com.mojang.serialization.MapCodec<T>);
  public net.neoforged.neoforge.common.crafting.IngredientType(com.mojang.serialization.MapCodec<T>, net.minecraft.network.codec.StreamCodec<? super net.minecraft.network.RegistryFriendlyByteBuf, T>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public com.mojang.serialization.MapCodec<T> codec();
  public net.minecraft.network.codec.StreamCodec<? super net.minecraft.network.RegistryFriendlyByteBuf, T> streamCodec();
}
