Compiled from "IFactoryController.java"
public interface com.minecolonies.api.colony.requestsystem.factory.IFactoryController {
  public default <Input> com.minecolonies.api.colony.requestsystem.factory.IFactory<Input, ?> getFactoryForInput(java.lang.String) throws java.lang.IllegalArgumentException;
  public abstract <Input> com.minecolonies.api.colony.requestsystem.factory.IFactory<Input, ?> getFactoryForInput(com.google.common.reflect.TypeToken<? extends Input>) throws java.lang.IllegalArgumentException;
  public abstract <Input, Output> com.minecolonies.api.colony.requestsystem.factory.IFactory<Input, Output> getFactoryForIO(com.google.common.reflect.TypeToken<? extends Input>, com.google.common.reflect.TypeToken<? extends Output>) throws java.lang.IllegalArgumentException;
  public default <Output> com.minecolonies.api.colony.requestsystem.factory.IFactory<?, Output> getFactoryForOutput(java.lang.String) throws java.lang.IllegalArgumentException;
  public default <Output> com.minecolonies.api.colony.requestsystem.factory.IFactory<?, Output> getFactoryForOutput(short) throws java.lang.IllegalArgumentException;
  public abstract <Output> com.minecolonies.api.colony.requestsystem.factory.IFactory<?, Output> getFactoryForOutput(com.google.common.reflect.TypeToken<? extends Output>) throws java.lang.IllegalArgumentException;
  public abstract <Output> com.minecolonies.api.colony.requestsystem.factory.IFactory<?, Output> getFactoryForSerializationId(short) throws java.lang.IllegalArgumentException;
  public abstract <Input, Output> void registerNewFactory(com.minecolonies.api.colony.requestsystem.factory.IFactory<Input, Output>) throws java.lang.IllegalArgumentException;
  public abstract <Output> net.minecraft.nbt.CompoundTag serializeTag(net.minecraft.core.HolderLookup$Provider, Output) throws java.lang.IllegalArgumentException;
  public default <Output> net.minecraft.nbt.Tag serializeList(net.minecraft.core.HolderLookup$Provider, java.util.Collection<Output>);
  public abstract <Output> Output deserializeTag(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag) throws java.lang.IllegalArgumentException;
  public abstract <Output> Output deserialize(net.minecraft.network.RegistryFriendlyByteBuf) throws java.lang.IllegalArgumentException;
  public default <Output> java.util.Collection<Output> deserializeList(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.ListTag);
  public abstract <Output> void serialize(net.minecraft.network.RegistryFriendlyByteBuf, Output) throws java.lang.IllegalArgumentException;
  public abstract <Input, Output> Output getNewInstance(com.google.common.reflect.TypeToken<? extends Output>, Input, java.lang.Object...) throws java.lang.IllegalArgumentException, java.lang.ClassCastException;
  public abstract <Output> Output getNewInstance(com.google.common.reflect.TypeToken<? extends Output>) throws java.lang.IllegalArgumentException, java.lang.ClassCastException;
  public abstract <Output> void registerNewTypeOverrideHandler(com.minecolonies.api.colony.requestsystem.factory.ITypeOverrideHandler<Output>);
}
