Compiled from "IDeliverable.java"
public interface com.minecolonies.api.colony.requestsystem.requestable.IDeliverable extends com.minecolonies.api.colony.requestsystem.requestable.IRetryable {
  public abstract boolean matches(net.minecraft.world.item.ItemStack);
  public abstract int getCount();
  public abstract int getMinimumCount();
  public abstract net.minecraft.world.item.ItemStack getResult();
  public abstract void setResult(net.minecraft.world.item.ItemStack);
  public abstract com.minecolonies.api.colony.requestsystem.requestable.IDeliverable copyWithCount(int);
  public default boolean canBeResolvedByBuilding();
}
