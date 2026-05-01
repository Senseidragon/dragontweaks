Compiled from "ICondition.java"
public interface net.neoforged.neoforge.common.conditions.ICondition {
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.common.conditions.ICondition> CODEC;
  public static final com.mojang.serialization.Codec<java.util.List<net.neoforged.neoforge.common.conditions.ICondition>> LIST_CODEC;
  public static <V, T> java.util.Optional<T> getConditionally(com.mojang.serialization.Codec<T>, com.mojang.serialization.DynamicOps<V>, V);
  public static <V, T> java.util.Optional<T> getWithConditionalCodec(com.mojang.serialization.Codec<java.util.Optional<T>>, com.mojang.serialization.DynamicOps<V>, V);
  public static <V, T> java.util.Optional<T> getWithWithConditionsCodec(com.mojang.serialization.Codec<java.util.Optional<net.neoforged.neoforge.common.conditions.WithConditions<T>>>, com.mojang.serialization.DynamicOps<V>, V);
  public static <V> boolean conditionsMatched(com.mojang.serialization.DynamicOps<V>, V);
  public static void writeConditions(net.minecraft.core.HolderLookup$Provider, com.google.gson.JsonObject, net.neoforged.neoforge.common.conditions.ICondition...);
  public static void writeConditions(net.minecraft.core.HolderLookup$Provider, com.google.gson.JsonObject, java.util.List<net.neoforged.neoforge.common.conditions.ICondition>);
  public static void writeConditions(com.mojang.serialization.DynamicOps<com.google.gson.JsonElement>, com.google.gson.JsonObject, java.util.List<net.neoforged.neoforge.common.conditions.ICondition>);
  public abstract boolean test(net.neoforged.neoforge.common.conditions.ICondition$IContext);
  public abstract com.mojang.serialization.MapCodec<? extends net.neoforged.neoforge.common.conditions.ICondition> codec();
}
