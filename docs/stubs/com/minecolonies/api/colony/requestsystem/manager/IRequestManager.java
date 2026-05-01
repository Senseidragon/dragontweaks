Compiled from "IRequestManager.java"
public interface com.minecolonies.api.colony.requestsystem.manager.IRequestManager extends net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag>, com.minecolonies.api.tileentities.ITickable {
  public abstract com.minecolonies.api.colony.IColony getColony();
  public abstract com.minecolonies.api.colony.requestsystem.factory.IFactoryController getFactoryController();
  public abstract <T extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable> com.minecolonies.api.colony.requestsystem.token.IToken<?> createRequest(com.minecolonies.api.colony.requestsystem.requester.IRequester, T) throws java.lang.IllegalArgumentException;
  public abstract void assignRequest(com.minecolonies.api.colony.requestsystem.token.IToken<?>) throws java.lang.IllegalArgumentException;
  public default <T extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable> com.minecolonies.api.colony.requestsystem.token.IToken<?> createAndAssignRequest(com.minecolonies.api.colony.requestsystem.requester.IRequester, T) throws java.lang.IllegalArgumentException;
  public abstract com.minecolonies.api.colony.requestsystem.token.IToken<?> reassignRequest(com.minecolonies.api.colony.requestsystem.token.IToken<?>, java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>>) throws java.lang.IllegalArgumentException;
  public abstract com.minecolonies.api.colony.requestsystem.request.IRequest<?> getRequestForToken(com.minecolonies.api.colony.requestsystem.token.IToken<?>) throws java.lang.IllegalArgumentException;
  public abstract com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?> getResolverForToken(com.minecolonies.api.colony.requestsystem.token.IToken<?>) throws java.lang.IllegalArgumentException;
  public abstract com.minecolonies.api.colony.requestsystem.resolver.IRequestResolver<?> getResolverForRequest(com.minecolonies.api.colony.requestsystem.token.IToken<?>) throws java.lang.IllegalArgumentException;
  public abstract void updateRequestState(com.minecolonies.api.colony.requestsystem.token.IToken<?>, com.minecolonies.api.colony.requestsystem.request.RequestState) throws java.lang.IllegalArgumentException;
  public abstract void overruleRequest(com.minecolonies.api.colony.requestsystem.token.IToken<?>, net.minecraft.world.item.ItemStack) throws java.lang.IllegalArgumentException;
  public abstract void onProviderAddedToColony(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolverProvider) throws java.lang.IllegalArgumentException;
  public abstract void onRequesterRemovedFromColony(com.minecolonies.api.colony.requestsystem.requester.IRequester) throws java.lang.IllegalArgumentException;
  public abstract void onProviderRemovedFromColony(com.minecolonies.api.colony.requestsystem.resolver.IRequestResolverProvider) throws java.lang.IllegalArgumentException;
  public abstract void onColonyUpdate(java.util.function.Predicate<com.minecolonies.api.colony.requestsystem.request.IRequest<?>>);
  public abstract com.minecolonies.api.colony.requestsystem.resolver.player.IPlayerRequestResolver getPlayerResolver();
  public abstract com.minecolonies.api.colony.requestsystem.resolver.retrying.IRetryingRequestResolver getRetryingRequestResolver();
  public abstract com.minecolonies.api.colony.requestsystem.data.IDataStoreManager getDataStoreManager();
  public abstract void reset();
  public abstract boolean isDirty();
  public abstract void setDirty(boolean);
  public abstract void markDirty();
  public abstract void log(java.lang.String);
  public abstract void serialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract void deserialize(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, net.minecraft.network.RegistryFriendlyByteBuf);
}
