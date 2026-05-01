Compiled from "DataMapLoader.java"
public class net.neoforged.neoforge.registries.DataMapLoader implements net.minecraft.server.packs.resources.PreparableReloadListener {
  public static final java.lang.String PATH;
  public net.neoforged.neoforge.registries.DataMapLoader(net.neoforged.neoforge.common.conditions.ICondition$IContext, net.minecraft.core.RegistryAccess);
  public java.util.concurrent.CompletableFuture<java.lang.Void> reload(net.minecraft.server.packs.resources.PreparableReloadListener$PreparationBarrier, net.minecraft.server.packs.resources.ResourceManager, net.minecraft.util.profiling.ProfilerFiller, net.minecraft.util.profiling.ProfilerFiller, java.util.concurrent.Executor, java.util.concurrent.Executor);
  public void apply();
  public static java.lang.String getFolderLocation(net.minecraft.resources.ResourceLocation);
}
