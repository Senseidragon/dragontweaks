Compiled from "SingleFluidIngredient.java"
public class net.neoforged.neoforge.fluids.crafting.SingleFluidIngredient extends net.neoforged.neoforge.fluids.crafting.FluidIngredient {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.fluids.crafting.SingleFluidIngredient> CODEC;
  public net.neoforged.neoforge.fluids.crafting.SingleFluidIngredient(net.minecraft.core.Holder<net.minecraft.world.level.material.Fluid>);
  public boolean test(net.neoforged.neoforge.fluids.FluidStack);
  public boolean isSimple();
  public net.neoforged.neoforge.fluids.crafting.FluidIngredientType<?> getType();
  public int hashCode();
  public boolean equals(java.lang.Object);
  public net.minecraft.core.Holder<net.minecraft.world.level.material.Fluid> fluid();
  public boolean test(java.lang.Object);
}
