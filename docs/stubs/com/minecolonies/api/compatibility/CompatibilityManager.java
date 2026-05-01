Compiled from "CompatibilityManager.java"
public class com.minecolonies.api.compatibility.CompatibilityManager implements com.minecolonies.api.compatibility.ICompatibilityManager {
  public com.minecolonies.api.compatibility.CompatibilityManager();
  public void discover(net.minecraft.world.item.crafting.RecipeManager, net.minecraft.world.level.Level);
  public void serialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public void deserialize(net.minecraft.network.RegistryFriendlyByteBuf, net.minecraft.client.multiplayer.ClientLevel);
  public java.util.List<net.minecraft.world.item.ItemStack> getListOfAllItems();
  public java.util.List<net.minecraft.world.item.ItemStack> getListOfMatchingItems(java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public java.util.Set<com.minecolonies.api.crafting.ItemStorage> getSetOfAllItems();
  public boolean isPlantable(net.minecraft.world.item.ItemStack);
  public boolean isLuckyBlock(net.minecraft.world.level.block.Block);
  public net.minecraft.world.item.ItemStack getSaplingForLeaf(net.minecraft.world.level.block.Block);
  public java.util.Set<com.minecolonies.api.crafting.ItemStorage> getCopyOfSaplings();
  public java.util.Set<com.minecolonies.api.crafting.ItemStorage> getFuel();
  public java.util.Set<com.minecolonies.api.crafting.ItemStorage> getFood();
  public java.util.Set<com.minecolonies.api.crafting.ItemStorage> getEdibles(int);
  public java.util.Set<com.minecolonies.api.crafting.ItemStorage> getSmeltableOres();
  public java.util.Map<net.minecraft.world.item.Item, net.minecraft.world.item.crafting.RecipeHolder<com.minecolonies.api.crafting.CompostRecipe>> getCopyOfCompostRecipes();
  public java.util.Set<com.minecolonies.api.crafting.ItemStorage> getCompostInputs();
  public java.util.Set<com.minecolonies.api.crafting.ItemStorage> getCopyOfPlantables();
  public java.util.Set<com.minecolonies.api.crafting.ItemStorage> getImmutableFlowers();
  public boolean isOre(net.minecraft.world.level.block.state.BlockState);
  public boolean isOre(net.minecraft.world.item.ItemStack);
  public boolean isMineableOre(net.minecraft.world.item.ItemStack);
  public boolean isBreakableOre(net.minecraft.world.item.ItemStack);
  public void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public void connectLeafToSapling(net.minecraft.world.level.block.Block, net.minecraft.world.item.ItemStack);
  public net.minecraft.world.item.CreativeModeTab getCreativeTab(com.minecolonies.api.crafting.ItemStorage);
  public int getCreativeTabKey(com.minecolonies.api.crafting.ItemStorage);
  public com.google.common.collect.ImmutableSet<net.minecraft.resources.ResourceLocation> getAllMonsters();
  public com.minecolonies.core.util.FurnaceRecipes getFurnaceRecipes();
  public int getNumberOfSaplings();
  public java.util.Optional<net.minecraft.world.item.DyeColor> getDyeColor(net.minecraft.world.item.ItemStack);
}
