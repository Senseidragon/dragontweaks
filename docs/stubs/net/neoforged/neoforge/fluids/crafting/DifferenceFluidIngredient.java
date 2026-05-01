Compiled from "DifferenceFluidIngredient.java"
public final class net.neoforged.neoforge.fluids.crafting.DifferenceFluidIngredient extends net.neoforged.neoforge.fluids.crafting.FluidIngredient {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.fluids.crafting.DifferenceFluidIngredient> CODEC;
  public net.neoforged.neoforge.fluids.crafting.DifferenceFluidIngredient(net.neoforged.neoforge.fluids.crafting.FluidIngredient, net.neoforged.neoforge.fluids.crafting.FluidIngredient);
  public java.util.stream.Stream<net.neoforged.neoforge.fluids.FluidStack> generateStacks();
  public boolean test(net.neoforged.neoforge.fluids.FluidStack);
  public boolean isSimple();
  public net.neoforged.neoforge.fluids.crafting.FluidIngredientType<?> getType();
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(net.neoforged.neoforge.fluids.crafting.FluidIngredient, net.neoforged.neoforge.fluids.crafting.FluidIngredient);
  public net.neoforged.neoforge.fluids.crafting.FluidIngredient base();
  public net.neoforged.neoforge.fluids.crafting.FluidIngredient subtracted();
  public int hashCode();
  public boolean equals(java.lang.Object);
  public boolean test(java.lang.Object);
}
