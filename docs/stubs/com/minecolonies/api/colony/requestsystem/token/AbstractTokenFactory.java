Compiled from "AbstractTokenFactory.java"
public abstract class com.minecolonies.api.colony.requestsystem.token.AbstractTokenFactory<I> implements com.minecolonies.api.colony.requestsystem.token.ITokenFactory<I, com.minecolonies.api.colony.requestsystem.token.StandardToken> {
  public static final java.lang.String NBT_MSB;
  public static final java.lang.String NBT_LSB;
  public com.minecolonies.api.colony.requestsystem.token.AbstractTokenFactory();
  public com.google.common.reflect.TypeToken<com.minecolonies.api.colony.requestsystem.token.StandardToken> getFactoryOutputType();
  public net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.token.StandardToken);
  public com.minecolonies.api.colony.requestsystem.token.StandardToken deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag);
  public void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.token.StandardToken, net.minecraft.network.RegistryFriendlyByteBuf);
  public com.minecolonies.api.colony.requestsystem.token.StandardToken deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf) throws java.lang.Throwable;
  public java.lang.Object deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf) throws java.lang.Throwable;
  public void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, net.minecraft.network.RegistryFriendlyByteBuf);
  public java.lang.Object deserialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.nbt.CompoundTag) throws java.lang.Throwable;
  public net.minecraft.nbt.CompoundTag serialize(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object);
}
