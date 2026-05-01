Compiled from "ICompatibilityManager.java"
public interface com.minecolonies.api.compatibility.ICompatibilityManager {
  public abstract void discover(net.minecraft.world.item.crafting.RecipeManager, net.minecraft.world.level.Level);
  public abstract void serialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract void deserialize(net.minecraft.network.RegistryFriendlyByteBuf, net.minecraft.client.multiplayer.ClientLevel);
  public abstract net.minecraft.world.item.ItemStack getSaplingForLeaf(net.minecraft.world.level.block.Block);
  public abstract java.util.Set<com.minecolonies.api.crafting.ItemStorage> getCopyOfSaplings();
  public abstract java.util.Set<com.minecolonies.api.crafting.ItemStorage> getFuel();
  public abstract java.util.Set<com.minecolonies.api.crafting.ItemStorage> getFood();
  public abstract java.util.Set<com.minecolonies.api.crafting.ItemStorage> getEdibles(int);
  public abstract java.util.Set<com.minecolonies.api.crafting.ItemStorage> getSmeltableOres();
  public abstract boolean isMineableOre(net.minecraft.world.item.ItemStack);
  public abstract boolean isBreakableOre(net.minecraft.world.item.ItemStack);
  public abstract java.util.Map<net.minecraft.world.item.Item, net.minecraft.world.item.crafting.RecipeHolder<com.minecolonies.api.crafting.CompostRecipe>> getCopyOfCompostRecipes();
  public abstract java.util.Set<com.minecolonies.api.crafting.ItemStorage> getCompostInputs();
  public abstract java.util.Set<com.minecolonies.api.crafting.ItemStorage> getCopyOfPlantables();
  public abstract java.util.Set<com.minecolonies.api.crafting.ItemStorage> getImmutableFlowers();
  public abstract com.google.common.collect.ImmutableSet<net.minecraft.resources.ResourceLocation> getAllMonsters();
  public abstract boolean isOre(net.minecraft.world.level.block.state.BlockState);
  public abstract boolean isOre(net.minecraft.world.item.ItemStack);
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getListOfAllItems();
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getListOfMatchingItems(java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public abstract java.util.Set<com.minecolonies.api.crafting.ItemStorage> getSetOfAllItems();
  public abstract void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void connectLeafToSapling(net.minecraft.world.level.block.Block, net.minecraft.world.item.ItemStack);
  public abstract boolean isPlantable(net.minecraft.world.item.ItemStack);
  public abstract boolean isLuckyBlock(net.minecraft.world.level.block.Block);
  public abstract net.minecraft.world.item.CreativeModeTab getCreativeTab(com.minecolonies.api.crafting.ItemStorage);
  public abstract int getCreativeTabKey(com.minecolonies.api.crafting.ItemStorage);
  public abstract com.minecolonies.core.util.FurnaceRecipes getFurnaceRecipes();
  public abstract int getNumberOfSaplings();
  public abstract java.util.Optional<net.minecraft.world.item.DyeColor> getDyeColor(net.minecraft.world.item.ItemStack);
}
