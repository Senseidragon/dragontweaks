Compiled from "TagFluidIngredient.java"
public class net.neoforged.neoforge.fluids.crafting.TagFluidIngredient extends net.neoforged.neoforge.fluids.crafting.FluidIngredient {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.fluids.crafting.TagFluidIngredient> CODEC;
  public net.neoforged.neoforge.fluids.crafting.TagFluidIngredient(net.minecraft.tags.TagKey<net.minecraft.world.level.material.Fluid>);
  public boolean test(net.neoforged.neoforge.fluids.FluidStack);
  public boolean isSimple();
  public net.neoforged.neoforge.fluids.crafting.FluidIngredientType<?> getType();
  public int hashCode();
  public boolean equals(java.lang.Object);
  public net.minecraft.tags.TagKey<net.minecraft.world.level.material.Fluid> tag();
  public boolean test(java.lang.Object);
}
