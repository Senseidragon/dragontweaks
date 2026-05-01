Compiled from "IntegerFactory.java"
public class com.minecolonies.api.colony.requestsystem.factory.standard.IntegerFactory implements com.minecolonies.api.colony.requestsystem.factory.IFactory<com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, java.lang.Integer> {
  public com.minecolonies.api.colony.requestsystem.factory.standard.IntegerFactory();
  public com.google.common.reflect.TypeToken<? extends java.lang.Integer> getFactoryOutputType();
  public com.google.common.reflect.TypeToken<? extends com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput> getFactoryInputType();
  public java.lang.Integer getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, java.lang.Object...) throws java.lang.IllegalArgumentException;
  public net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Integer);
  public java.lang.Integer deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag);
  public void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Integer, net.minecraft.network.RegistryFriendlyByteBuf);
  public java.lang.Integer deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf) throws java.lang.Throwable;
  public short getSerializationId();
  public java.lang.Object deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf) throws java.lang.Throwable;
  public void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, net.minecraft.network.RegistryFriendlyByteBuf);
  public java.lang.Object deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag) throws java.lang.Throwable;
  public net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object);
  public java.lang.Object getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, java.lang.Object[]) throws java.lang.IllegalArgumentException;
}
