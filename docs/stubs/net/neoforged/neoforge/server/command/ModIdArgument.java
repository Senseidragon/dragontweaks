Compiled from "ModIdArgument.java"
public class net.neoforged.neoforge.server.command.ModIdArgument implements com.mojang.brigadier.arguments.ArgumentType<java.lang.String> {
  public net.neoforged.neoforge.server.command.ModIdArgument();
  public static net.neoforged.neoforge.server.command.ModIdArgument modIdArgument();
  public java.lang.String parse(com.mojang.brigadier.StringReader) throws com.mojang.brigadier.exceptions.CommandSyntaxException;
  public <S> java.util.concurrent.CompletableFuture<com.mojang.brigadier.suggestion.Suggestions> listSuggestions(com.mojang.brigadier.context.CommandContext<S>, com.mojang.brigadier.suggestion.SuggestionsBuilder);
  public java.util.Collection<java.lang.String> getExamples();
  public java.lang.Object parse(com.mojang.brigadier.StringReader) throws com.mojang.brigadier.exceptions.CommandSyntaxException;
}
