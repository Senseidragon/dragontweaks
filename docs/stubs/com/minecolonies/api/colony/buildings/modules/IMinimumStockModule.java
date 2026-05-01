Compiled from "IMinimumStockModule.java"
public interface com.minecolonies.api.colony.buildings.modules.IMinimumStockModule extends com.minecolonies.api.colony.buildings.modules.IBuildingModule {
  public abstract void removeMinimumStock(net.minecraft.world.item.ItemStack);
  public abstract void addMinimumStock(net.minecraft.world.item.ItemStack, int);
  public abstract boolean isStocked(net.minecraft.world.item.ItemStack);
}
