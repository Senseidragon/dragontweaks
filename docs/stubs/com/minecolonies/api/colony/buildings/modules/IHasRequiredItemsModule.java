Compiled from "IHasRequiredItemsModule.java"
public interface com.minecolonies.api.colony.buildings.modules.IHasRequiredItemsModule extends com.minecolonies.api.colony.buildings.modules.IBuildingModule {
  public abstract java.util.Map<java.util.function.Predicate<net.minecraft.world.item.ItemStack>, net.minecraft.util.Tuple<java.lang.Integer, java.lang.Boolean>> getRequiredItemsAndAmount();
  public default java.util.Map<com.minecolonies.api.crafting.ItemStorage, java.lang.Integer> reservedStacks();
  public abstract java.util.Map<com.minecolonies.api.crafting.ItemStorage, java.lang.Integer> reservedStacksExcluding(com.minecolonies.api.colony.requestsystem.request.IRequest<? extends com.minecolonies.api.colony.requestsystem.requestable.IDeliverable>);
}
