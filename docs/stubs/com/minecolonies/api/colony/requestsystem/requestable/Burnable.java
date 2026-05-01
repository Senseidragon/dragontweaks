Compiled from "Burnable.java"
public class com.minecolonies.api.colony.requestsystem.requestable.Burnable implements com.minecolonies.api.colony.requestsystem.requestable.IDeliverable {
  public com.minecolonies.api.colony.requestsystem.requestable.Burnable(int);
  public com.minecolonies.api.colony.requestsystem.requestable.Burnable(int, net.minecraft.world.item.ItemStack);
  public static net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.requestable.Burnable);
  public static com.minecolonies.api.colony.requestsystem.requestable.Burnable deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag);
  public static void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.colony.requestsystem.requestable.Burnable);
  public static com.minecolonies.api.colony.requestsystem.requestable.Burnable deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf);
  public boolean matches(net.minecraft.world.item.ItemStack);
  public void setResult(net.minecraft.world.item.ItemStack);
  public com.minecolonies.api.colony.requestsystem.requestable.IDeliverable copyWithCount(int);
  public int getCount();
  public int getMinimumCount();
  public net.minecraft.world.item.ItemStack getResult();
  public boolean equals(java.lang.Object);
  public int hashCode();
  public java.util.Set<com.google.common.reflect.TypeToken<?>> getSuperClasses();
}
