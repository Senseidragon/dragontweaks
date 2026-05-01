Compiled from "EnumArgument.java"
public class net.neoforged.neoforge.server.command.EnumArgument<T extends java.lang.Enum<T>> implements com.mojang.brigadier.arguments.ArgumentType<T> {
  public static <R extends java.lang.Enum<R>> net.neoforged.neoforge.server.command.EnumArgument<R> enumArgument(java.lang.Class<R>);
  public T parse(com.mojang.brigadier.StringReader) throws com.mojang.brigadier.exceptions.CommandSyntaxException;
  public <S> java.util.concurrent.CompletableFuture<com.mojang.brigadier.suggestion.Suggestions> listSuggestions(com.mojang.brigadier.context.CommandContext<S>, com.mojang.brigadier.suggestion.SuggestionsBuilder);
  public java.util.Collection<java.lang.String> getExamples();
  public java.lang.Object parse(com.mojang.brigadier.StringReader) throws com.mojang.brigadier.exceptions.CommandSyntaxException;
}
