Compiled from "NotCondition.java"
public final class net.neoforged.neoforge.common.conditions.NotCondition extends java.lang.Record implements net.neoforged.neoforge.common.conditions.ICondition {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.conditions.NotCondition> CODEC;
  public net.neoforged.neoforge.common.conditions.NotCondition(net.neoforged.neoforge.common.conditions.ICondition);
  public boolean test(net.neoforged.neoforge.common.conditions.ICondition$IContext);
  public com.mojang.serialization.MapCodec<? extends net.neoforged.neoforge.common.conditions.ICondition> codec();
  public java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.neoforged.neoforge.common.conditions.ICondition value();
}
