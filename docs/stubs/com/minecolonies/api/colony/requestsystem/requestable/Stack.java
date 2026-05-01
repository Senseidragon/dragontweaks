Compiled from "Stack.java"
public class com.minecolonies.api.colony.requestsystem.requestable.Stack implements com.minecolonies.api.colony.requestsystem.requestable.IConcreteDeliverable {
  public com.minecolonies.api.colony.requestsystem.requestable.Stack(net.minecraft.world.item.ItemStack);
  public com.minecolonies.api.colony.requestsystem.requestable.Stack(net.minecraft.world.item.ItemStack, boolean);
  public com.minecolonies.api.colony.requestsystem.requestable.Stack(net.minecraft.world.item.ItemStack, int, int);
  public com.minecolonies.api.colony.requestsystem.requestable.Stack(com.minecolonies.api.crafting.ItemStorage);
  public com.minecolonies.api.colony.requestsystem.requestable.Stack(net.minecraft.world.item.ItemStack, int, int, boolean);
  public com.minecolonies.api.colony.requestsystem.requestable.Stack(net.minecraft.world.item.ItemStack, boolean, boolean, net.minecraft.world.item.ItemStack, int, int);
  public com.minecolonies.api.colony.requestsystem.requestable.Stack(net.minecraft.world.item.ItemStack, boolean, boolean, net.minecraft.world.item.ItemStack, int, int, boolean);
  public static net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.requestable.Stack);
  public static com.minecolonies.api.colony.requestsystem.requestable.Stack deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag);
  public static void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.colony.requestsystem.requestable.Stack);
  public static com.minecolonies.api.colony.requestsystem.requestable.Stack deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf);
  public boolean matches(net.minecraft.world.item.ItemStack);
  public int getCount();
  public int getMinimumCount();
  public net.minecraft.world.item.ItemStack getStack();
  public void setResult(net.minecraft.world.item.ItemStack);
  public boolean matchNBT();
  public boolean matchDamage();
  public com.minecolonies.api.colony.requestsystem.requestable.IDeliverable copyWithCount(int);
  public net.minecraft.world.item.ItemStack getResult();
  public boolean equals(java.lang.Object);
  public int hashCode();
  public boolean canBeResolvedByBuilding();
  public void setCanBeResolvedByBuilding(boolean);
  public java.util.List<net.minecraft.world.item.ItemStack> getRequestedItems();
  public java.util.Set<com.google.common.reflect.TypeToken<?>> getSuperClasses();
}
