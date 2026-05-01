Compiled from "DeferredBlock.java"
public class net.neoforged.neoforge.registries.DeferredBlock<T extends net.minecraft.world.level.block.Block> extends net.neoforged.neoforge.registries.DeferredHolder<net.minecraft.world.level.block.Block, T> implements net.minecraft.world.level.ItemLike {
  public net.minecraft.world.item.ItemStack toStack();
  public net.minecraft.world.item.ItemStack toStack(int);
  public static <T extends net.minecraft.world.level.block.Block> net.neoforged.neoforge.registries.DeferredBlock<T> createBlock(net.minecraft.resources.ResourceLocation);
  public static <T extends net.minecraft.world.level.block.Block> net.neoforged.neoforge.registries.DeferredBlock<T> createBlock(net.minecraft.resources.ResourceKey<net.minecraft.world.level.block.Block>);
  public net.minecraft.world.item.Item asItem();
}
