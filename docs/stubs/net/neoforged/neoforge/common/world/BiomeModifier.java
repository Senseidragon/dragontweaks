Compiled from "BiomeModifier.java"
public interface net.neoforged.neoforge.common.world.BiomeModifier {
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.common.world.BiomeModifier> DIRECT_CODEC;
  public static final com.mojang.serialization.Codec<net.minecraft.core.Holder<net.neoforged.neoforge.common.world.BiomeModifier>> REFERENCE_CODEC;
  public static final com.mojang.serialization.Codec<net.minecraft.core.HolderSet<net.neoforged.neoforge.common.world.BiomeModifier>> LIST_CODEC;
  public abstract void modify(net.minecraft.core.Holder<net.minecraft.world.level.biome.Biome>, net.neoforged.neoforge.common.world.BiomeModifier$Phase, net.neoforged.neoforge.common.world.ModifiableBiomeInfo$BiomeInfo$Builder);
  public abstract com.mojang.serialization.MapCodec<? extends net.neoforged.neoforge.common.world.BiomeModifier> codec();
}
