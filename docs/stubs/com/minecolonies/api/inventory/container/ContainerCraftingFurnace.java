Compiled from "ContainerCraftingFurnace.java"
public class com.minecolonies.api.inventory.container.ContainerCraftingFurnace extends net.minecraft.world.inventory.AbstractContainerMenu {
  public final net.minecraft.core.BlockPos buildingPos;
  public static com.minecolonies.api.inventory.container.ContainerCraftingFurnace fromFriendlyByteBuf(int, net.minecraft.world.entity.player.Inventory, net.minecraft.network.RegistryFriendlyByteBuf);
  public com.minecolonies.api.inventory.container.ContainerCraftingFurnace(int, net.minecraft.world.entity.player.Inventory, net.minecraft.core.BlockPos, int);
  public void clicked(int, int, net.minecraft.world.inventory.ClickType, net.minecraft.world.entity.player.Player);
  public void setFurnaceInput(net.minecraft.world.item.ItemStack);
  public boolean stillValid(net.minecraft.world.entity.player.Player);
  public net.minecraft.world.item.ItemStack quickMoveStack(net.minecraft.world.entity.player.Player, int);
  public boolean canTakeItemForPickAll(net.minecraft.world.item.ItemStack, net.minecraft.world.inventory.Slot);
  public net.minecraft.world.entity.player.Player getPlayer();
  public net.minecraft.world.level.Level getWorldObj();
  public net.minecraft.core.BlockPos getPos();
  public int getModuleId();
}
