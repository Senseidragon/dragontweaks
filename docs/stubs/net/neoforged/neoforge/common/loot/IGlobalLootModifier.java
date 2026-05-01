Compiled from "IGlobalLootModifier.java"
public interface net.neoforged.neoforge.common.loot.IGlobalLootModifier {
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.common.loot.IGlobalLootModifier> DIRECT_CODEC;
  public static final com.mojang.serialization.Codec<java.util.Optional<net.neoforged.neoforge.common.conditions.WithConditions<net.neoforged.neoforge.common.loot.IGlobalLootModifier>>> CONDITIONAL_CODEC;
  public static final com.mojang.serialization.Codec<net.minecraft.world.level.storage.loot.predicates.LootItemCondition[]> LOOT_CONDITIONS_CODEC;
  public abstract it.unimi.dsi.fastutil.objects.ObjectArrayList<net.minecraft.world.item.ItemStack> apply(it.unimi.dsi.fastutil.objects.ObjectArrayList<net.minecraft.world.item.ItemStack>, net.minecraft.world.level.storage.loot.LootContext);
  public abstract com.mojang.serialization.MapCodec<? extends net.neoforged.neoforge.common.loot.IGlobalLootModifier> codec();
}
