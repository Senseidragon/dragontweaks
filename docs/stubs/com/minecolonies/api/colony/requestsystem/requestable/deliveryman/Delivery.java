Compiled from "Delivery.java"
public class com.minecolonies.api.colony.requestsystem.requestable.deliveryman.Delivery extends com.minecolonies.api.colony.requestsystem.requestable.deliveryman.AbstractDeliverymanRequestable {
  public com.minecolonies.api.colony.requestsystem.requestable.deliveryman.Delivery(com.minecolonies.api.colony.requestsystem.location.ILocation, com.minecolonies.api.colony.requestsystem.location.ILocation, net.minecraft.world.item.ItemStack, int);
  public static net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.requestable.deliveryman.Delivery);
  public static com.minecolonies.api.colony.requestsystem.requestable.deliveryman.Delivery deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag);
  public static void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.colony.requestsystem.requestable.deliveryman.Delivery);
  public static com.minecolonies.api.colony.requestsystem.requestable.deliveryman.Delivery deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf);
  public com.minecolonies.api.colony.requestsystem.location.ILocation getStart();
  public com.minecolonies.api.colony.requestsystem.location.ILocation getTarget();
  public net.minecraft.world.item.ItemStack getStack();
  public boolean equals(java.lang.Object);
  public int hashCode();
  public java.lang.String toString();
  public java.util.Set<com.google.common.reflect.TypeToken<?>> getSuperClasses();
}
