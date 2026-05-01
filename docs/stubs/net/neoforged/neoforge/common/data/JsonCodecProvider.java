Compiled from "JsonCodecProvider.java"
public abstract class net.neoforged.neoforge.common.data.JsonCodecProvider<T> implements net.minecraft.data.DataProvider {
  public net.neoforged.neoforge.common.data.JsonCodecProvider(net.minecraft.data.PackOutput, net.minecraft.data.PackOutput$Target, java.lang.String, net.minecraft.server.packs.PackType, com.mojang.serialization.Codec<T>, java.util.concurrent.CompletableFuture<net.minecraft.core.HolderLookup$Provider>, java.lang.String, net.neoforged.neoforge.common.data.ExistingFileHelper);
  public java.util.concurrent.CompletableFuture<?> run(net.minecraft.data.CachedOutput);
  public java.lang.String getName();
  public void unconditional(net.minecraft.resources.ResourceLocation, T);
  public void conditionally(net.minecraft.resources.ResourceLocation, java.util.function.Consumer<net.neoforged.neoforge.common.conditions.WithConditions$Builder<T>>);
}
