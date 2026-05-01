Compiled from "NeoForgeExtraCodecs.java"
public class net.neoforged.neoforge.common.util.NeoForgeExtraCodecs {
  public net.neoforged.neoforge.common.util.NeoForgeExtraCodecs();
  public static <T> com.mojang.serialization.MapCodec<T> aliasedFieldOf(com.mojang.serialization.Codec<T>, java.lang.String...);
  public static <T> com.mojang.serialization.MapCodec<T> optionalFieldAlwaysWrite(com.mojang.serialization.Codec<T>, java.lang.String, T);
  public static <T> com.mojang.serialization.MapCodec<T> mapWithAlternative(com.mojang.serialization.MapCodec<T>, com.mojang.serialization.MapCodec<? extends T>);
  public static <T> com.mojang.serialization.MapCodec<java.util.Set<T>> singularOrPluralCodec(com.mojang.serialization.Codec<T>, java.lang.String);
  public static <T> com.mojang.serialization.MapCodec<java.util.Set<T>> singularOrPluralCodec(com.mojang.serialization.Codec<T>, java.lang.String, java.lang.String);
  public static <T> com.mojang.serialization.MapCodec<java.util.Set<T>> singularOrPluralCodecNotEmpty(com.mojang.serialization.Codec<T>, java.lang.String);
  public static <T> com.mojang.serialization.MapCodec<java.util.Set<T>> singularOrPluralCodecNotEmpty(com.mojang.serialization.Codec<T>, java.lang.String, java.lang.String);
  public static <T> com.mojang.serialization.Codec<java.util.Set<T>> setOf(com.mojang.serialization.Codec<T>);
  public static <A> com.mojang.serialization.Codec<A> decodeOnly(com.mojang.serialization.Decoder<A>);
  public static <A> com.mojang.serialization.Codec<java.util.List<A>> listWithOptionalElements(com.mojang.serialization.Codec<java.util.Optional<A>>);
  public static <A> com.mojang.serialization.Codec<java.util.List<A>> listWithoutEmpty(com.mojang.serialization.Codec<java.util.List<java.util.Optional<A>>>);
  public static <T> com.mojang.serialization.Codec<T> withAlternative(com.mojang.serialization.Codec<T>, com.mojang.serialization.Codec<T>);
  public static <T> com.mojang.serialization.MapCodec<T> withAlternative(com.mojang.serialization.MapCodec<T>, com.mojang.serialization.MapCodec<T>);
  public static <A, E, B> com.mojang.serialization.MapCodec<com.mojang.datafixers.util.Either<E, B>> dispatchMapOrElse(com.mojang.serialization.Codec<A>, java.util.function.Function<? super E, ? extends A>, java.util.function.Function<? super A, ? extends com.mojang.serialization.MapCodec<? extends E>>, com.mojang.serialization.MapCodec<B>);
  public static <F, S> com.mojang.serialization.MapCodec<com.mojang.datafixers.util.Either<F, S>> xor(com.mojang.serialization.MapCodec<F>, com.mojang.serialization.MapCodec<S>);
}
