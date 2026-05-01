Compiled from "DataComponentFluidIngredient.java"
public class net.neoforged.neoforge.fluids.crafting.DataComponentFluidIngredient extends net.neoforged.neoforge.fluids.crafting.FluidIngredient {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.fluids.crafting.DataComponentFluidIngredient> CODEC;
  public net.neoforged.neoforge.fluids.crafting.DataComponentFluidIngredient(net.minecraft.core.HolderSet<net.minecraft.world.level.material.Fluid>, net.minecraft.core.component.DataComponentPredicate, boolean);
  public boolean test(net.neoforged.neoforge.fluids.FluidStack);
  public java.util.stream.Stream<net.neoforged.neoforge.fluids.FluidStack> generateStacks();
  public boolean isSimple();
  public net.neoforged.neoforge.fluids.crafting.FluidIngredientType<?> getType();
  public int hashCode();
  public boolean equals(java.lang.Object);
  public net.minecraft.core.HolderSet<net.minecraft.world.level.material.Fluid> fluids();
  public net.minecraft.core.component.DataComponentPredicate components();
  public boolean isStrict();
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(boolean, net.neoforged.neoforge.fluids.FluidStack);
  public static <T> net.neoforged.neoforge.fluids.crafting.FluidIngredient of(boolean, net.minecraft.core.component.DataComponentType<? super T>, T, net.minecraft.world.level.material.Fluid...);
  public static <T> net.neoforged.neoforge.fluids.crafting.FluidIngredient of(boolean, java.util.function.Supplier<? extends net.minecraft.core.component.DataComponentType<? super T>>, T, net.minecraft.world.level.material.Fluid...);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(boolean, net.minecraft.core.component.DataComponentMap, net.minecraft.world.level.material.Fluid...);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(boolean, net.minecraft.core.component.DataComponentMap, net.minecraft.core.Holder<net.minecraft.world.level.material.Fluid>...);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(boolean, net.minecraft.core.component.DataComponentMap, net.minecraft.core.HolderSet<net.minecraft.world.level.material.Fluid>);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(boolean, net.minecraft.core.component.DataComponentPredicate, net.minecraft.core.Holder<net.minecraft.world.level.material.Fluid>...);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(boolean, net.minecraft.core.component.DataComponentPredicate, net.minecraft.world.level.material.Fluid...);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(boolean, net.minecraft.core.component.DataComponentPredicate, net.minecraft.core.HolderSet<net.minecraft.world.level.material.Fluid>);
  public boolean test(java.lang.Object);
}
