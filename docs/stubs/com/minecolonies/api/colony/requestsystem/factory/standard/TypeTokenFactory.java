Compiled from "TypeTokenFactory.java"
public class com.minecolonies.api.colony.requestsystem.factory.standard.TypeTokenFactory implements com.minecolonies.api.colony.requestsystem.factory.IFactory<java.lang.Class<?>, com.google.common.reflect.TypeToken<?>> {
  public com.minecolonies.api.colony.requestsystem.factory.standard.TypeTokenFactory();
  public com.google.common.reflect.TypeToken<? extends com.google.common.reflect.TypeToken<?>> getFactoryOutputType();
  public com.google.common.reflect.TypeToken<? extends java.lang.Class<?>> getFactoryInputType();
  public com.google.common.reflect.TypeToken<?> getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Class<?>, java.lang.Object...) throws java.lang.IllegalArgumentException;
  public net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.google.common.reflect.TypeToken<?>);
  public com.google.common.reflect.TypeToken<?> deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag) throws java.lang.Throwable;
  public void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.google.common.reflect.TypeToken<?>, net.minecraft.network.RegistryFriendlyByteBuf);
  public com.google.common.reflect.TypeToken<?> deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf) throws java.lang.Throwable;
  public short getSerializationId();
  public java.lang.Object deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf) throws java.lang.Throwable;
  public void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, net.minecraft.network.RegistryFriendlyByteBuf);
  public java.lang.Object deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag) throws java.lang.Throwable;
  public net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object);
  public java.lang.Object getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, java.lang.Object[]) throws java.lang.IllegalArgumentException;
}
