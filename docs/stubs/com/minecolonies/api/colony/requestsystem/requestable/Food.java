Compiled from "Food.java"
public class com.minecolonies.api.colony.requestsystem.requestable.Food implements com.minecolonies.api.colony.requestsystem.requestable.IDeliverable {
  public com.minecolonies.api.colony.requestsystem.requestable.Food(int, int);
  public com.minecolonies.api.colony.requestsystem.requestable.Food(int, net.minecraft.world.item.ItemStack, int);
  public com.minecolonies.api.colony.requestsystem.requestable.Food(int, net.minecraft.world.item.ItemStack, java.util.List<com.minecolonies.api.crafting.ItemStorage>, int);
  public com.minecolonies.api.colony.requestsystem.requestable.Food(int, java.util.List<com.minecolonies.api.crafting.ItemStorage>, int);
  public static net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.requestable.Food);
  public static com.minecolonies.api.colony.requestsystem.requestable.Food deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag);
  public static void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.colony.requestsystem.requestable.Food);
  public static com.minecolonies.api.colony.requestsystem.requestable.Food deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf);
  public boolean matches(net.minecraft.world.item.ItemStack);
  public void setResult(net.minecraft.world.item.ItemStack);
  public com.minecolonies.api.colony.requestsystem.requestable.IDeliverable copyWithCount(int);
  public int getCount();
  public int getMinimumCount();
  public net.minecraft.world.item.ItemStack getResult();
  public java.util.List<com.minecolonies.api.crafting.ItemStorage> getExclusionList();
  public boolean equals(java.lang.Object);
  public int hashCode();
  public java.util.Set<com.google.common.reflect.TypeToken<?>> getSuperClasses();
  public boolean canBeResolvedByBuilding();
}
