Compiled from "StructureModifier.java"
public interface net.neoforged.neoforge.common.world.StructureModifier {
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.common.world.StructureModifier> DIRECT_CODEC;
  public static final com.mojang.serialization.Codec<net.minecraft.core.Holder<net.neoforged.neoforge.common.world.StructureModifier>> REFERENCE_CODEC;
  public static final com.mojang.serialization.Codec<net.minecraft.core.HolderSet<net.neoforged.neoforge.common.world.StructureModifier>> LIST_CODEC;
  public abstract void modify(net.minecraft.core.Holder<net.minecraft.world.level.levelgen.structure.Structure>, net.neoforged.neoforge.common.world.StructureModifier$Phase, net.neoforged.neoforge.common.world.ModifiableStructureInfo$StructureInfo$Builder);
  public abstract com.mojang.serialization.MapCodec<? extends net.neoforged.neoforge.common.world.StructureModifier> codec();
}
