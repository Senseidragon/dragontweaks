Compiled from "DeferredRegister.java"
public class net.neoforged.neoforge.registries.DeferredRegister<T> {
  public static <T> net.neoforged.neoforge.registries.DeferredRegister<T> create(net.minecraft.core.Registry<T>, java.lang.String);
  public static <T> net.neoforged.neoforge.registries.DeferredRegister<T> create(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>>, java.lang.String);
  public static <B> net.neoforged.neoforge.registries.DeferredRegister<B> create(net.minecraft.resources.ResourceLocation, java.lang.String);
  public static net.neoforged.neoforge.registries.DeferredRegister$Items createItems(java.lang.String);
  public static net.neoforged.neoforge.registries.DeferredRegister$Blocks createBlocks(java.lang.String);
  public static net.neoforged.neoforge.registries.DeferredRegister$DataComponents createDataComponents(net.minecraft.resources.ResourceKey<net.minecraft.core.Registry<net.minecraft.core.component.DataComponentType<?>>>, java.lang.String);
  public static net.neoforged.neoforge.registries.DeferredRegister$DataComponents createDataComponents(java.lang.String);
  public <I extends T> net.neoforged.neoforge.registries.DeferredHolder<T, I> register(java.lang.String, java.util.function.Supplier<? extends I>);
  public <I extends T> net.neoforged.neoforge.registries.DeferredHolder<T, I> register(java.lang.String, java.util.function.Function<net.minecraft.resources.ResourceLocation, ? extends I>);
  public net.minecraft.core.Registry<T> makeRegistry(java.util.function.Consumer<net.neoforged.neoforge.registries.RegistryBuilder<T>>);
  public java.util.function.Supplier<net.minecraft.core.Registry<T>> getRegistry();
  public net.minecraft.tags.TagKey<T> createTagKey(java.lang.String);
  public net.minecraft.tags.TagKey<T> createTagKey(net.minecraft.resources.ResourceLocation);
  public void addAlias(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation);
  public void register(net.neoforged.bus.api.IEventBus);
  public java.util.Collection<net.neoforged.neoforge.registries.DeferredHolder<T, ? extends T>> getEntries();
  public net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<T>> getRegistryKey();
  public net.minecraft.resources.ResourceLocation getRegistryName();
  public java.lang.String getNamespace();
}
