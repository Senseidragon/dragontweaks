Compiled from "TagEmptyCondition.java"
public final class net.neoforged.neoforge.common.conditions.TagEmptyCondition extends java.lang.Record implements net.neoforged.neoforge.common.conditions.ICondition {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.conditions.TagEmptyCondition> CODEC;
  public net.neoforged.neoforge.common.conditions.TagEmptyCondition(java.lang.String);
  public net.neoforged.neoforge.common.conditions.TagEmptyCondition(java.lang.String, java.lang.String);
  public net.neoforged.neoforge.common.conditions.TagEmptyCondition(net.minecraft.resources.ResourceLocation);
  public net.neoforged.neoforge.common.conditions.TagEmptyCondition(net.minecraft.tags.TagKey<net.minecraft.world.item.Item>);
  public boolean test(net.neoforged.neoforge.common.conditions.ICondition$IContext);
  public com.mojang.serialization.MapCodec<? extends net.neoforged.neoforge.common.conditions.ICondition> codec();
  public java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.tags.TagKey<net.minecraft.world.item.Item> tag();
}
