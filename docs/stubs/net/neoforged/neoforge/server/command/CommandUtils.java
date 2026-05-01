Compiled from "CommandUtils.java"
public final class net.neoforged.neoforge.server.command.CommandUtils {
  public static java.util.concurrent.CompletableFuture<com.mojang.brigadier.suggestion.Suggestions> suggestRegistries(com.mojang.brigadier.context.CommandContext<net.minecraft.commands.CommandSourceStack>, com.mojang.brigadier.suggestion.SuggestionsBuilder);
  public static <T extends net.minecraft.core.Registry<?>> com.mojang.brigadier.suggestion.SuggestionProvider<net.minecraft.commands.CommandSourceStack> suggestFromRegistry(java.util.function.Function<net.minecraft.core.Registry<?>, java.lang.Iterable<net.minecraft.resources.ResourceLocation>>, java.lang.String, net.minecraft.resources.ResourceKey<net.minecraft.core.Registry<T>>);
  public static <T> java.util.Optional<net.minecraft.resources.ResourceKey<T>> getResourceKey(com.mojang.brigadier.context.CommandContext<net.minecraft.commands.CommandSourceStack>, java.lang.String, net.minecraft.resources.ResourceKey<net.minecraft.core.Registry<T>>);
  public static net.minecraft.network.chat.MutableComponent makeTranslatableWithFallback(java.lang.String, java.lang.Object...);
  public static net.minecraft.network.chat.MutableComponent makeTranslatableWithFallback(java.lang.String);
}
