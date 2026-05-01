Compiled from "SizedFluidIngredient.java"
public final class net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient {
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient> FLAT_CODEC;
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient> NESTED_CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient> STREAM_CODEC;
  public static net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient of(net.minecraft.world.level.material.Fluid, int);
  public static net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient of(net.neoforged.neoforge.fluids.FluidStack);
  public static net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient of(net.minecraft.tags.TagKey<net.minecraft.world.level.material.Fluid>, int);
  public net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient(net.neoforged.neoforge.fluids.crafting.FluidIngredient, int);
  public net.neoforged.neoforge.fluids.crafting.FluidIngredient ingredient();
  public int amount();
  public boolean test(net.neoforged.neoforge.fluids.FluidStack);
  public net.neoforged.neoforge.fluids.FluidStack[] getFluids();
  public boolean equals(java.lang.Object);
  public int hashCode();
  public java.lang.String toString();
}
