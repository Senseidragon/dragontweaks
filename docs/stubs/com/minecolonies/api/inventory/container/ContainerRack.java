Compiled from "ContainerRack.java"
public class com.minecolonies.api.inventory.container.ContainerRack extends net.minecraft.world.inventory.AbstractContainerMenu {
  public final com.minecolonies.api.tileentities.AbstractTileEntityRack rack;
  public final com.minecolonies.api.tileentities.AbstractTileEntityRack neighborRack;
  public static com.minecolonies.api.inventory.container.ContainerRack fromFriendlyByteBuf(int, net.minecraft.world.entity.player.Inventory, net.minecraft.network.RegistryFriendlyByteBuf);
  public com.minecolonies.api.inventory.container.ContainerRack(int, net.minecraft.world.entity.player.Inventory, net.minecraft.core.BlockPos, net.minecraft.core.BlockPos);
  public void clicked(int, int, net.minecraft.world.inventory.ClickType, net.minecraft.world.entity.player.Player);
  public net.minecraft.world.item.ItemStack quickMoveStack(net.minecraft.world.entity.player.Player, int);
  public boolean stillValid(net.minecraft.world.entity.player.Player);
}
