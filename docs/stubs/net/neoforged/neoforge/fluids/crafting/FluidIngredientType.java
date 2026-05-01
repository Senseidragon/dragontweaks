Compiled from "FluidIngredientType.java"
public final class net.neoforged.neoforge.fluids.crafting.FluidIngredientType<T extends net.neoforged.neoforge.fluids.crafting.FluidIngredient> extends java.lang.Record {
  public net.neoforged.neoforge.fluids.crafting.FluidIngredientType(com.mojang.serialization.MapCodec<T>);
  public net.neoforged.neoforge.fluids.crafting.FluidIngredientType(com.mojang.serialization.MapCodec<T>, net.minecraft.network.codec.StreamCodec<? super net.minecraft.network.RegistryFriendlyByteBuf, T>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public com.mojang.serialization.MapCodec<T> codec();
  public net.minecraft.network.codec.StreamCodec<? super net.minecraft.network.RegistryFriendlyByteBuf, T> streamCodec();
}
