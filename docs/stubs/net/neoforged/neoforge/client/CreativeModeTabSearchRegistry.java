Compiled from "CreativeModeTabSearchRegistry.java"
public class net.neoforged.neoforge.client.CreativeModeTabSearchRegistry {
  public net.neoforged.neoforge.client.CreativeModeTabSearchRegistry();
  public static java.util.Map<net.minecraft.world.item.CreativeModeTab, net.minecraft.client.multiplayer.SessionSearchTrees$Key> getNameSearchKeys();
  public static java.util.Map<net.minecraft.world.item.CreativeModeTab, net.minecraft.client.multiplayer.SessionSearchTrees$Key> getTagSearchKeys();
  public static net.minecraft.client.multiplayer.SessionSearchTrees$Key getNameSearchKey(net.minecraft.world.item.CreativeModeTab);
  public static net.minecraft.client.multiplayer.SessionSearchTrees$Key getTagSearchKey(net.minecraft.world.item.CreativeModeTab);
  public static java.util.concurrent.CompletableFuture<net.minecraft.client.searchtree.SearchTree<net.minecraft.world.item.ItemStack>> getNameSearchTree(net.minecraft.client.multiplayer.SessionSearchTrees$Key);
  public static void putNameSearchTree(net.minecraft.client.multiplayer.SessionSearchTrees$Key, java.util.concurrent.CompletableFuture<net.minecraft.client.searchtree.SearchTree<net.minecraft.world.item.ItemStack>>);
  public static java.util.concurrent.CompletableFuture<net.minecraft.client.searchtree.SearchTree<net.minecraft.world.item.ItemStack>> getTagSearchTree(net.minecraft.client.multiplayer.SessionSearchTrees$Key);
  public static void putTagSearchTree(net.minecraft.client.multiplayer.SessionSearchTrees$Key, java.util.concurrent.CompletableFuture<net.minecraft.client.searchtree.SearchTree<net.minecraft.world.item.ItemStack>>);
}
