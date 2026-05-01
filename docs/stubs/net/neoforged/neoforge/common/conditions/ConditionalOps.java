Compiled from "ConditionalOps.java"
public class net.neoforged.neoforge.common.conditions.ConditionalOps<T> extends net.minecraft.resources.RegistryOps<T> {
  public static final java.lang.String DEFAULT_CONDITIONS_KEY;
  public static final java.lang.String CONDITIONAL_VALUE_KEY;
  public net.neoforged.neoforge.common.conditions.ConditionalOps(net.minecraft.resources.RegistryOps<T>, net.neoforged.neoforge.common.conditions.ICondition$IContext);
  public static com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.conditions.ICondition$IContext> retrieveContext();
  public static <T> com.mojang.serialization.Codec<java.util.Optional<T>> createConditionalCodec(com.mojang.serialization.Codec<T>);
  public static <T> com.mojang.serialization.Codec<java.util.Optional<T>> createConditionalCodec(com.mojang.serialization.Codec<T>, java.lang.String);
  public static <T> com.mojang.serialization.Codec<java.util.List<T>> decodeListWithElementConditions(com.mojang.serialization.Codec<T>);
  public static <T> com.mojang.serialization.Codec<java.util.Optional<net.neoforged.neoforge.common.conditions.WithConditions<T>>> createConditionalCodecWithConditions(com.mojang.serialization.Codec<T>);
  public static <T> com.mojang.serialization.Codec<java.util.Optional<net.neoforged.neoforge.common.conditions.WithConditions<T>>> createConditionalCodecWithConditions(com.mojang.serialization.Codec<T>, java.lang.String);
}
