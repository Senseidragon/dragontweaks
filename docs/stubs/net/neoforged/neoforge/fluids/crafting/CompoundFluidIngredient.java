Compiled from "CompoundFluidIngredient.java"
public final class net.neoforged.neoforge.fluids.crafting.CompoundFluidIngredient extends net.neoforged.neoforge.fluids.crafting.FluidIngredient {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.fluids.crafting.CompoundFluidIngredient> CODEC;
  public net.neoforged.neoforge.fluids.crafting.CompoundFluidIngredient(java.util.List<? extends net.neoforged.neoforge.fluids.crafting.FluidIngredient>);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(net.neoforged.neoforge.fluids.crafting.FluidIngredient...);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(java.util.List<net.neoforged.neoforge.fluids.crafting.FluidIngredient>);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(java.util.stream.Stream<net.neoforged.neoforge.fluids.crafting.FluidIngredient>);
  public java.util.stream.Stream<net.neoforged.neoforge.fluids.FluidStack> generateStacks();
  public boolean test(net.neoforged.neoforge.fluids.FluidStack);
  public boolean isSimple();
  public net.neoforged.neoforge.fluids.crafting.FluidIngredientType<?> getType();
  public int hashCode();
  public boolean equals(java.lang.Object);
  public java.util.List<net.neoforged.neoforge.fluids.crafting.FluidIngredient> children();
  public boolean test(java.lang.Object);
}
