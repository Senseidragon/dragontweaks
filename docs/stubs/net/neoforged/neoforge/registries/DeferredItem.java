Compiled from "DeferredItem.java"
public class net.neoforged.neoforge.registries.DeferredItem<T extends net.minecraft.world.item.Item> extends net.neoforged.neoforge.registries.DeferredHolder<net.minecraft.world.item.Item, T> implements net.minecraft.world.level.ItemLike {
  public net.minecraft.world.item.ItemStack toStack();
  public net.minecraft.world.item.ItemStack toStack(int);
  public static <T extends net.minecraft.world.item.Item> net.neoforged.neoforge.registries.DeferredItem<T> createItem(net.minecraft.resources.ResourceLocation);
  public static <T extends net.minecraft.world.item.Item> net.neoforged.neoforge.registries.DeferredItem<T> createItem(net.minecraft.resources.ResourceKey<net.minecraft.world.item.Item>);
  public net.minecraft.world.item.Item asItem();
}
