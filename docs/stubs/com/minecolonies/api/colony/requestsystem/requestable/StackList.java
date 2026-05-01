Compiled from "StackList.java"
public class com.minecolonies.api.colony.requestsystem.requestable.StackList implements com.minecolonies.api.colony.requestsystem.requestable.IConcreteDeliverable,com.minecolonies.api.colony.requestsystem.requestable.INonExhaustiveDeliverable {
  public com.minecolonies.api.colony.requestsystem.requestable.StackList(java.util.List<net.minecraft.world.item.ItemStack>, java.lang.String, int);
  public com.minecolonies.api.colony.requestsystem.requestable.StackList(java.util.List<net.minecraft.world.item.ItemStack>, java.lang.String, int, int);
  public com.minecolonies.api.colony.requestsystem.requestable.StackList(java.util.List<net.minecraft.world.item.ItemStack>, java.lang.String, int, int, int);
  public com.minecolonies.api.colony.requestsystem.requestable.StackList(java.util.List<net.minecraft.world.item.ItemStack>, boolean, boolean, boolean, net.minecraft.world.item.ItemStack, java.lang.String, int, int, int);
  public static net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.requestable.StackList);
  public static com.minecolonies.api.colony.requestsystem.requestable.StackList deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag);
  public static void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.colony.requestsystem.requestable.StackList);
  public static com.minecolonies.api.colony.requestsystem.requestable.StackList deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf);
  public boolean matches(net.minecraft.world.item.ItemStack);
  public int getMinimumCount();
  public int getCount();
  public int getLeftOver();
  public java.util.List<net.minecraft.world.item.ItemStack> getStacks();
  public void setResult(net.minecraft.world.item.ItemStack);
  public com.minecolonies.api.colony.requestsystem.requestable.IDeliverable copyWithCount(int);
  public net.minecraft.world.item.ItemStack getResult();
  public boolean equals(java.lang.Object);
  public int hashCode();
  public java.lang.String getDescription();
  public java.util.List<net.minecraft.world.item.ItemStack> getRequestedItems();
  public java.util.Set<com.google.common.reflect.TypeToken<?>> getSuperClasses();
  public com.minecolonies.api.colony.requestsystem.requestable.StackList(net.minecraft.tags.TagKey<net.minecraft.world.item.Item>, net.minecraft.server.level.ServerLevel, java.lang.String, int, int, int);
}
