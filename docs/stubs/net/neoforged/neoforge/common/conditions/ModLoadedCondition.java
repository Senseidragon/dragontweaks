Compiled from "ModLoadedCondition.java"
public final class net.neoforged.neoforge.common.conditions.ModLoadedCondition extends java.lang.Record implements net.neoforged.neoforge.common.conditions.ICondition {
  public static com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.conditions.ModLoadedCondition> CODEC;
  public net.neoforged.neoforge.common.conditions.ModLoadedCondition(java.lang.String);
  public boolean test(net.neoforged.neoforge.common.conditions.ICondition$IContext);
  public com.mojang.serialization.MapCodec<? extends net.neoforged.neoforge.common.conditions.ICondition> codec();
  public java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.lang.String modid();
}
