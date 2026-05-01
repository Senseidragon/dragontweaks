Compiled from "SimpleFluidContent.java"
public class net.neoforged.neoforge.fluids.SimpleFluidContent implements net.minecraft.core.component.DataComponentHolder {
  public static final net.neoforged.neoforge.fluids.SimpleFluidContent EMPTY;
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.fluids.SimpleFluidContent> CODEC;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, net.neoforged.neoforge.fluids.SimpleFluidContent> STREAM_CODEC;
  public static net.neoforged.neoforge.fluids.SimpleFluidContent copyOf(net.neoforged.neoforge.fluids.FluidStack);
  public net.neoforged.neoforge.fluids.FluidStack copy();
  public boolean isEmpty();
  public net.minecraft.world.level.material.Fluid getFluid();
  public net.minecraft.core.Holder<net.minecraft.world.level.material.Fluid> getFluidHolder();
  public boolean is(net.minecraft.tags.TagKey<net.minecraft.world.level.material.Fluid>);
  public boolean is(net.minecraft.world.level.material.Fluid);
  public boolean is(java.util.function.Predicate<net.minecraft.core.Holder<net.minecraft.world.level.material.Fluid>>);
  public boolean is(net.minecraft.core.Holder<net.minecraft.world.level.material.Fluid>);
  public boolean is(net.minecraft.core.HolderSet<net.minecraft.world.level.material.Fluid>);
  public int getAmount();
  public net.neoforged.neoforge.fluids.FluidType getFluidType();
  public boolean is(net.neoforged.neoforge.fluids.FluidType);
  public boolean matches(net.neoforged.neoforge.fluids.FluidStack);
  public boolean isSameFluid(net.neoforged.neoforge.fluids.FluidStack);
  public boolean isSameFluidSameComponents(net.neoforged.neoforge.fluids.FluidStack);
  public boolean isSameFluidSameComponents(net.neoforged.neoforge.fluids.SimpleFluidContent);
  public net.minecraft.core.component.DataComponentMap getComponents();
  public boolean equals(java.lang.Object);
  public int hashCode();
}
