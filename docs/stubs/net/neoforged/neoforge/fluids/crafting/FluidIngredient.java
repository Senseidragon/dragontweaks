Compiled from "FluidIngredient.java"
public abstract class net.neoforged.neoforge.fluids.crafting.FluidIngredient implements java.util.function.Predicate<net.neoforged.neoforge.fluids.FluidStack> {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.fluids.crafting.FluidIngredient> MAP_CODEC_NONEMPTY;
  public static final com.mojang.serialization.Codec<java.util.List<net.neoforged.neoforge.fluids.crafting.FluidIngredient>> LIST_CODEC;
  public static final com.mojang.serialization.Codec<java.util.List<net.neoforged.neoforge.fluids.crafting.FluidIngredient>> LIST_CODEC_NON_EMPTY;
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.fluids.crafting.FluidIngredient> CODEC;
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.fluids.crafting.FluidIngredient> CODEC_NON_EMPTY;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, net.neoforged.neoforge.fluids.crafting.FluidIngredient> STREAM_CODEC;
  public net.neoforged.neoforge.fluids.crafting.FluidIngredient();
  public final net.neoforged.neoforge.fluids.FluidStack[] getStacks();
  public abstract boolean test(net.neoforged.neoforge.fluids.FluidStack);
  public abstract boolean isSimple();
  public abstract net.neoforged.neoforge.fluids.crafting.FluidIngredientType<?> getType();
  public final boolean isEmpty();
  public final boolean hasNoFluids();
  public abstract int hashCode();
  public abstract boolean equals(java.lang.Object);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient empty();
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of();
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(net.neoforged.neoforge.fluids.FluidStack...);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient of(net.minecraft.world.level.material.Fluid...);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient single(net.neoforged.neoforge.fluids.FluidStack);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient single(net.minecraft.world.level.material.Fluid);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient single(net.minecraft.core.Holder<net.minecraft.world.level.material.Fluid>);
  public static net.neoforged.neoforge.fluids.crafting.FluidIngredient tag(net.minecraft.tags.TagKey<net.minecraft.world.level.material.Fluid>);
  public boolean test(java.lang.Object);
}
