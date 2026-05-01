Compiled from "ItemStorage.java"
public class com.minecolonies.api.crafting.ItemStorage {
  public com.minecolonies.api.crafting.ItemStorage(net.minecraft.world.item.ItemStack, int, boolean);
  public com.minecolonies.api.crafting.ItemStorage(net.minecraft.world.item.ItemStack, int, boolean, boolean);
  public com.minecolonies.api.crafting.ItemStorage(net.minecraft.world.item.ItemStack, int);
  public com.minecolonies.api.crafting.ItemStorage(net.minecraft.world.item.ItemStack, boolean, boolean);
  public com.minecolonies.api.crafting.ItemStorage(net.minecraft.world.item.ItemStack, boolean);
  public com.minecolonies.api.crafting.ItemStorage(net.minecraft.world.item.ItemStack);
  public com.minecolonies.api.crafting.ItemStorage(net.minecraft.world.item.Item, int);
  public com.minecolonies.api.crafting.ItemStorage(net.minecraft.world.item.Item);
  public com.minecolonies.api.crafting.ItemStorage(net.minecraft.core.HolderLookup$Provider, com.google.gson.JsonObject);
  public static com.minecolonies.api.crafting.ItemStorage getItemStackOfListMatchingPredicate(java.util.List<com.minecolonies.api.crafting.ItemStorage>, java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public net.minecraft.world.item.ItemStack getItemStack();
  public int getAmount();
  public void setAmount(int);
  public boolean ignoreDamageValue();
  public boolean ignoreNBT();
  public java.lang.String toString();
  public int hashCode();
  public boolean equals(java.lang.Object);
  public boolean matchDefinitionEquals(com.minecolonies.api.crafting.ItemStorage);
  public net.minecraft.world.item.Item getItem();
  public int getDamageValue();
  public int getRemainingDurablityValue();
  public boolean isEmpty();
  public com.minecolonies.api.crafting.ItemStorage copy();
  public com.minecolonies.api.crafting.ImmutableItemStorage toImmutable();
}
