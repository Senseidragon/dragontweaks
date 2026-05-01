Compiled from "LanguageProvider.java"
public abstract class net.neoforged.neoforge.common.data.LanguageProvider implements net.minecraft.data.DataProvider {
  public net.neoforged.neoforge.common.data.LanguageProvider(net.minecraft.data.PackOutput, java.lang.String, java.lang.String);
  public java.util.concurrent.CompletableFuture<?> run(net.minecraft.data.CachedOutput);
  public java.lang.String getName();
  public void addBlock(java.util.function.Supplier<? extends net.minecraft.world.level.block.Block>, java.lang.String);
  public void add(net.minecraft.world.level.block.Block, java.lang.String);
  public void addItem(java.util.function.Supplier<? extends net.minecraft.world.item.Item>, java.lang.String);
  public void add(net.minecraft.world.item.Item, java.lang.String);
  public void addItemStack(java.util.function.Supplier<net.minecraft.world.item.ItemStack>, java.lang.String);
  public void add(net.minecraft.world.item.ItemStack, java.lang.String);
  public void addEffect(java.util.function.Supplier<? extends net.minecraft.world.effect.MobEffect>, java.lang.String);
  public void add(net.minecraft.world.effect.MobEffect, java.lang.String);
  public void addEntityType(java.util.function.Supplier<? extends net.minecraft.world.entity.EntityType<?>>, java.lang.String);
  public void add(net.minecraft.world.entity.EntityType<?>, java.lang.String);
  public void addTag(java.util.function.Supplier<? extends net.minecraft.tags.TagKey<?>>, java.lang.String);
  public void add(net.minecraft.tags.TagKey<?>, java.lang.String);
  public void add(java.lang.String, java.lang.String);
  public void addDimension(net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level>, java.lang.String);
}
