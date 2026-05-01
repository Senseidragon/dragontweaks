Compiled from "ItemAbilityPredicate.java"
public final class net.neoforged.neoforge.common.advancements.critereon.ItemAbilityPredicate extends java.lang.Record implements net.minecraft.advancements.critereon.ItemSubPredicate {
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.common.advancements.critereon.ItemAbilityPredicate> CODEC;
  public static final net.minecraft.advancements.critereon.ItemSubPredicate$Type<net.neoforged.neoforge.common.advancements.critereon.ItemAbilityPredicate> TYPE;
  public net.neoforged.neoforge.common.advancements.critereon.ItemAbilityPredicate(net.neoforged.neoforge.common.ItemAbility);
  public boolean matches(net.minecraft.world.item.ItemStack);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.neoforged.neoforge.common.ItemAbility action();
}
