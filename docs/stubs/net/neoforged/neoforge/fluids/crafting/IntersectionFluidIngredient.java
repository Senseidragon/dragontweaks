Compiled from "IntersectionFluidIngredient.java"
public final class net.neoforged.neoforge.fluids.crafting.IntersectionFluidIngredient extends net.neoforged.neoforge.fluids.crafting.FluidIngredient {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.fluids.crafting.IntersectionFluidIngredient> CODEC;
  public net.neoforged.neoforge.fluids.crafting.IntersectionFluidIngredient(java.util.List<net.neoforged.neoforge.fluids.crafting.FluidIngredient>);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(net.neoforged.neoforge.fluids.crafting.FluidIngredient...);
  public boolean test(net.neoforged.neoforge.fluids.FluidStack);
  public java.util.stream.Stream<net.neoforged.neoforge.fluids.FluidStack> generateStacks();
  public boolean isSimple();
  public net.neoforged.neoforge.fluids.crafting.FluidIngredientType<?> getType();
  public java.util.List<net.neoforged.neoforge.fluids.crafting.FluidIngredient> children();
  public int hashCode();
  public boolean equals(java.lang.Object);
  public boolean test(java.lang.Object);
}
