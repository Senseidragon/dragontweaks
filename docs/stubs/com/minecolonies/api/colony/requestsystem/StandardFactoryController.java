Compiled from "StandardFactoryController.java"
public final class com.minecolonies.api.colony.requestsystem.StandardFactoryController implements com.minecolonies.api.colony.requestsystem.factory.IFactoryController {
  public static final java.lang.String NBT_TYPE;
  public static final java.lang.String NBT_DATA;
  public static final java.lang.String NEW_NBT_TYPE;
  public static void reset();
  public static com.minecolonies.api.colony.requestsystem.StandardFactoryController getInstance();
  public <INPUT, OUTPUT> com.minecolonies.api.colony.requestsystem.factory.IFactory<INPUT, OUTPUT> getFactoryForIO(com.google.common.reflect.TypeToken<? extends INPUT>, com.google.common.reflect.TypeToken<? extends OUTPUT>) throws java.lang.IllegalArgumentException;
  public <INPUT> com.minecolonies.api.colony.requestsystem.factory.IFactory<INPUT, ?> getFactoryForInput(com.google.common.reflect.TypeToken<? extends INPUT>) throws java.lang.IllegalArgumentException;
  public <OUTPUT> com.minecolonies.api.colony.requestsystem.factory.IFactory<?, OUTPUT> getFactoryForOutput(com.google.common.reflect.TypeToken<? extends OUTPUT>) throws java.lang.IllegalArgumentException;
  public <OUTPUT> com.minecolonies.api.colony.requestsystem.factory.IFactory<?, OUTPUT> getFactoryForSerializationId(short) throws java.lang.IllegalArgumentException;
  public <INPUT, OUTPUT> void registerNewFactory(com.minecolonies.api.colony.requestsystem.factory.IFactory<INPUT, OUTPUT>) throws java.lang.IllegalArgumentException;
  public <OUTPUT> net.minecraft.nbt.CompoundTag serializeTag(net.minecraft.core.HolderLookup$Provider, OUTPUT) throws java.lang.IllegalArgumentException;
  public <OUTPUT> OUTPUT deserializeTag(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag) throws java.lang.IllegalArgumentException;
  public <OUTPUT> void serialize(net.minecraft.network.RegistryFriendlyByteBuf, OUTPUT) throws java.lang.IllegalArgumentException;
  public <OUTPUT> OUTPUT deserialize(net.minecraft.network.RegistryFriendlyByteBuf) throws java.lang.IllegalArgumentException;
  public <INPUT, OUTPUT> OUTPUT getNewInstance(com.google.common.reflect.TypeToken<? extends OUTPUT>, INPUT, java.lang.Object...) throws java.lang.IllegalArgumentException, java.lang.ClassCastException;
  public <OUTPUT> OUTPUT getNewInstance(com.google.common.reflect.TypeToken<? extends OUTPUT>) throws java.lang.IllegalArgumentException;
  public <OUTPUT> void registerNewTypeOverrideHandler(com.minecolonies.api.colony.requestsystem.factory.ITypeOverrideHandler<OUTPUT>);
}
