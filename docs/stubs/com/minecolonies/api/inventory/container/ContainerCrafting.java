Compiled from "ContainerCrafting.java"
public class com.minecolonies.api.inventory.container.ContainerCrafting extends net.minecraft.world.inventory.AbstractContainerMenu {
  public final net.minecraft.world.inventory.CraftingContainer craftMatrix;
  public final net.minecraft.world.inventory.ResultContainer craftResult;
  public static com.minecolonies.api.inventory.container.ContainerCrafting fromFriendlyByteBuf(int, net.minecraft.world.entity.player.Inventory, net.minecraft.network.RegistryFriendlyByteBuf);
  public com.minecolonies.api.inventory.container.ContainerCrafting(int, net.minecraft.world.entity.player.Inventory, boolean, net.minecraft.core.BlockPos, int);
  public void slotsChanged(net.minecraft.world.Container);
  public boolean canSwitchRecipes();
  public void switchRecipes();
  public boolean stillValid(net.minecraft.world.entity.player.Player);
  public void clicked(int, int, net.minecraft.world.inventory.ClickType, net.minecraft.world.entity.player.Player);
  public net.minecraft.world.item.ItemStack handleSlotClick(net.minecraft.world.inventory.Slot, net.minecraft.world.item.ItemStack);
  public net.minecraft.world.item.ItemStack quickMoveStack(net.minecraft.world.entity.player.Player, int);
  public boolean canTakeItemForPickAll(net.minecraft.world.item.ItemStack, net.minecraft.world.inventory.Slot);
  public net.minecraft.world.level.Level getWorldObj();
  public net.minecraft.world.entity.player.Player getPlayer();
  public boolean isComplete();
  public net.minecraft.world.inventory.CraftingContainer getInv();
  public net.minecraft.core.BlockPos getPos();
  public java.util.List<net.minecraft.world.item.ItemStack> getRemainingItems();
  public int getModuleId();
}
