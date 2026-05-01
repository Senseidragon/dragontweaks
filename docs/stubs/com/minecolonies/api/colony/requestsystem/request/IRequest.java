Compiled from "IRequest.java"
public interface com.minecolonies.api.colony.requestsystem.request.IRequest<R extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable> {
  public default com.minecolonies.api.colony.requestsystem.manager.AssigningStrategy getStrategy();
  public abstract <T extends com.minecolonies.api.colony.requestsystem.token.IToken<?>> T getId();
  public abstract com.google.common.reflect.TypeToken<? extends R> getType();
  public abstract com.minecolonies.api.colony.requestsystem.request.RequestState getState();
  public abstract void setState(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.request.RequestState);
  public abstract com.minecolonies.api.colony.requestsystem.requester.IRequester getRequester();
  public abstract R getRequest();
  public abstract R getResult();
  public abstract void setResult(R);
  public abstract boolean hasResult();
  public abstract <T extends com.minecolonies.api.colony.requestsystem.token.IToken<?>> T getParent();
  public abstract <T extends com.minecolonies.api.colony.requestsystem.token.IToken<?>> void setParent(T);
  public abstract boolean hasParent();
  public abstract <T extends com.minecolonies.api.colony.requestsystem.token.IToken<?>> void addChild(T);
  public abstract <T extends com.minecolonies.api.colony.requestsystem.token.IToken<?>> void addChildren(T...);
  public abstract <T extends com.minecolonies.api.colony.requestsystem.token.IToken<?>> void addChildren(java.util.Collection<T>);
  public abstract <T extends com.minecolonies.api.colony.requestsystem.token.IToken<?>> void removeChild(T);
  public abstract <T extends com.minecolonies.api.colony.requestsystem.token.IToken<?>> void removeChildren(T...);
  public abstract <T extends com.minecolonies.api.colony.requestsystem.token.IToken<?>> void removeChildren(java.util.Collection<T>);
  public abstract boolean hasChildren();
  public abstract com.google.common.collect.ImmutableCollection<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getChildren();
  public abstract void childStateUpdated(com.minecolonies.api.colony.requestsystem.manager.IRequestManager, com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract boolean canBeDelivered();
  public abstract com.google.common.collect.ImmutableList<net.minecraft.world.item.ItemStack> getDeliveries();
  public abstract void overrideCurrentDeliveries(com.google.common.collect.ImmutableList<net.minecraft.world.item.ItemStack>);
  public abstract void addDelivery(net.minecraft.world.item.ItemStack);
  public abstract void addDelivery(java.util.List<net.minecraft.world.item.ItemStack>);
  public abstract net.minecraft.network.chat.Component getShortDisplayString();
  public abstract net.minecraft.network.chat.Component getLongDisplayString();
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getDisplayStacks();
  public abstract net.minecraft.resources.ResourceLocation getDisplayIcon();
  public abstract <T> java.util.Optional<T> getRequestOfType(java.lang.Class<T>);
  public abstract java.util.Set<com.google.common.reflect.TypeToken<?>> getSuperClasses();
  public abstract java.util.List<net.minecraft.network.chat.MutableComponent> getResolverToolTip(com.minecolonies.api.colony.IColonyView);
  public abstract void resetDeliveries();
}
