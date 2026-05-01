Compiled from "RequestTag.java"
public class com.minecolonies.api.colony.requestsystem.requestable.RequestTag implements com.minecolonies.api.colony.requestsystem.requestable.IDeliverable {
  public com.minecolonies.api.colony.requestsystem.requestable.RequestTag(net.minecraft.tags.TagKey<net.minecraft.world.item.Item>, int);
  public com.minecolonies.api.colony.requestsystem.requestable.RequestTag(net.minecraft.tags.TagKey<net.minecraft.world.item.Item>, int, int);
  public com.minecolonies.api.colony.requestsystem.requestable.RequestTag(net.minecraft.tags.TagKey<net.minecraft.world.item.Item>, net.minecraft.world.item.ItemStack, int, int);
  public boolean matches(net.minecraft.world.item.ItemStack);
  public int getCount();
  public int getMinimumCount();
  public net.minecraft.world.item.ItemStack getResult();
  public net.minecraft.tags.TagKey<net.minecraft.world.item.Item> getTag();
  public void setResult(net.minecraft.world.item.ItemStack);
  public com.minecolonies.api.colony.requestsystem.requestable.IDeliverable copyWithCount(int);
  public boolean equals(java.lang.Object);
  public int hashCode();
  public static net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.requestable.RequestTag);
  public static void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.colony.requestsystem.requestable.RequestTag);
  public static com.minecolonies.api.colony.requestsystem.requestable.RequestTag deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf);
  public static com.minecolonies.api.colony.requestsystem.requestable.RequestTag deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag);
  public java.util.Set<com.google.common.reflect.TypeToken<?>> getSuperClasses();
}
