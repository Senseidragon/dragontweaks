Compiled from "GatherDataEvent.java"
public class net.neoforged.neoforge.data.event.GatherDataEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.data.event.GatherDataEvent(net.neoforged.fml.ModContainer, net.minecraft.data.DataGenerator, net.neoforged.neoforge.data.event.GatherDataEvent$DataGeneratorConfig, net.neoforged.neoforge.common.data.ExistingFileHelper);
  public net.neoforged.fml.ModContainer getModContainer();
  public net.minecraft.server.packs.resources.ResourceManager getResourceManager(net.minecraft.server.packs.PackType);
  public java.util.Collection<java.nio.file.Path> getInputs();
  public java.util.Set<java.lang.String> getMods();
  public net.minecraft.data.DataGenerator getGenerator();
  public net.neoforged.neoforge.common.data.ExistingFileHelper getExistingFileHelper();
  public java.util.concurrent.CompletableFuture<net.minecraft.core.HolderLookup$Provider> getLookupProvider();
  public boolean includeServer();
  public boolean includeClient();
  public boolean includeDev();
  public boolean includeReports();
  public boolean validate();
  public <T extends net.minecraft.data.DataProvider> T addProvider(T);
  public <T extends net.minecraft.data.DataProvider> T createProvider(net.neoforged.neoforge.data.event.GatherDataEvent$DataProviderFromOutput<T>);
  public <T extends net.minecraft.data.DataProvider> T createProvider(net.neoforged.neoforge.data.event.GatherDataEvent$DataProviderFromOutputLookup<T>);
  public void createBlockAndItemTags(net.neoforged.neoforge.data.event.GatherDataEvent$DataProviderFromOutputLookup<net.minecraft.data.tags.TagsProvider<net.minecraft.world.level.block.Block>>, net.neoforged.neoforge.data.event.GatherDataEvent$ItemTagsProvider);
  public void createDatapackRegistryObjects(net.minecraft.core.RegistrySetBuilder);
  public void createDatapackRegistryObjects(net.minecraft.core.RegistrySetBuilder, java.util.Set<java.lang.String>);
  public void createDatapackRegistryObjects(net.minecraft.core.RegistrySetBuilder, java.util.Map<net.minecraft.resources.ResourceKey<?>, java.util.List<net.neoforged.neoforge.common.conditions.ICondition>>);
  public void createDatapackRegistryObjects(net.minecraft.core.RegistrySetBuilder, java.util.Map<net.minecraft.resources.ResourceKey<?>, java.util.List<net.neoforged.neoforge.common.conditions.ICondition>>, java.util.Set<java.lang.String>);
  public void createDatapackRegistryObjects(net.minecraft.core.RegistrySetBuilder, java.util.function.Consumer<java.util.function.BiConsumer<net.minecraft.resources.ResourceKey<?>, net.neoforged.neoforge.common.conditions.ICondition>>);
  public void createDatapackRegistryObjects(net.minecraft.core.RegistrySetBuilder, java.util.function.Consumer<java.util.function.BiConsumer<net.minecraft.resources.ResourceKey<?>, net.neoforged.neoforge.common.conditions.ICondition>>, java.util.Set<java.lang.String>);
}
