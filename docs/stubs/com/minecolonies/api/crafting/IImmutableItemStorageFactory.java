Compiled from "IImmutableItemStorageFactory.java"
public interface com.minecolonies.api.crafting.IImmutableItemStorageFactory extends com.minecolonies.api.colony.requestsystem.factory.IFactory<com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, com.minecolonies.api.crafting.ImmutableItemStorage> {
  public default com.minecolonies.api.crafting.ImmutableItemStorage getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, java.lang.Object...);
  public abstract com.minecolonies.api.crafting.ImmutableItemStorage getNewInstance(net.minecraft.world.item.ItemStack, int);
  public default java.lang.Object getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, java.lang.Object[]) throws java.lang.IllegalArgumentException;
}
