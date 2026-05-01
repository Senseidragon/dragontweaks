Compiled from "IFactory.java"
public interface com.minecolonies.api.colony.requestsystem.factory.IFactory<Input, Output> {
  public abstract com.google.common.reflect.TypeToken<? extends Output> getFactoryOutputType();
  public abstract com.google.common.reflect.TypeToken<? extends Input> getFactoryInputType();
  public abstract short getSerializationId();
  public abstract Output getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, Input, java.lang.Object...) throws java.lang.IllegalArgumentException;
  public abstract net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, Output);
  public abstract Output deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag) throws java.lang.Throwable;
  public abstract void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, Output, net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract Output deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf) throws java.lang.Throwable;
}
