Compiled from "Tool.java"
public class com.minecolonies.api.colony.requestsystem.requestable.Tool implements com.minecolonies.api.colony.requestsystem.requestable.IDeliverable {
  public com.minecolonies.api.colony.requestsystem.requestable.Tool(com.minecolonies.api.equipment.registry.EquipmentTypeEntry, java.lang.Integer, java.lang.Integer);
  public com.minecolonies.api.colony.requestsystem.requestable.Tool(com.minecolonies.api.equipment.registry.EquipmentTypeEntry, java.lang.Integer, java.lang.Integer, net.minecraft.world.item.ItemStack);
  public static net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.requestable.Tool);
  public com.minecolonies.api.equipment.registry.EquipmentTypeEntry getEquipmentType();
  public java.lang.Integer getMinLevel();
  public java.lang.Integer getMaxLevel();
  public static com.minecolonies.api.colony.requestsystem.requestable.Tool deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag);
  public static void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.colony.requestsystem.requestable.Tool);
  public static com.minecolonies.api.colony.requestsystem.requestable.Tool deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf);
  public boolean matches(net.minecraft.world.item.ItemStack);
  public int getCount();
  public int getMinimumCount();
  public net.minecraft.world.item.ItemStack getResult();
  public void setResult(net.minecraft.world.item.ItemStack);
  public com.minecolonies.api.colony.requestsystem.requestable.IDeliverable copyWithCount(int);
  public boolean isArmor();
  public int hashCode();
  public boolean equals(java.lang.Object);
  public java.util.Set<com.google.common.reflect.TypeToken<?>> getSuperClasses();
}
