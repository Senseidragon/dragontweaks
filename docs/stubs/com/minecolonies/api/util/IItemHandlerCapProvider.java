Compiled from "IItemHandlerCapProvider.java"
public interface com.minecolonies.api.util.IItemHandlerCapProvider {
  public default net.neoforged.neoforge.items.IItemHandler getItemHandlerCap(java.lang.Void);
  public default net.neoforged.neoforge.items.IItemHandler getItemHandlerCap();
  public abstract net.neoforged.neoforge.items.IItemHandler getItemHandlerCap(net.minecraft.core.Direction);
  public static com.minecolonies.api.util.IItemHandlerCapProvider wrap(net.minecraft.world.level.block.entity.BlockEntity);
  public static com.minecolonies.api.util.IItemHandlerCapProvider wrap(net.minecraft.world.entity.Entity, boolean);
  public static com.minecolonies.api.util.IItemHandlerCapProvider wrap(net.minecraft.world.item.ItemStack);
}
