Compiled from "ICitizenInventoryHandler.java"
public interface com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenInventoryHandler {
  public abstract int findFirstSlotInInventoryWith(net.minecraft.world.item.Item);
  public abstract int findFirstSlotInInventoryWith(net.minecraft.world.level.block.Block);
  public abstract int getItemCountInInventory(net.minecraft.world.level.block.Block);
  public abstract int getItemCountInInventory(net.minecraft.world.item.Item);
  public abstract boolean hasItemInInventory(net.minecraft.world.level.block.Block);
  public abstract boolean hasItemInInventory(net.minecraft.world.item.Item);
  public abstract boolean isInventoryFull();
}
