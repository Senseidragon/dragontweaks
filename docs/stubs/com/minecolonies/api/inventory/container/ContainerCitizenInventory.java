Compiled from "ContainerCitizenInventory.java"
public class com.minecolonies.api.inventory.container.ContainerCitizenInventory extends net.minecraft.world.inventory.AbstractContainerMenu {
  public static com.minecolonies.api.inventory.container.ContainerCitizenInventory fromFriendlyByteBuf(int, net.minecraft.world.entity.player.Inventory, net.minecraft.network.RegistryFriendlyByteBuf);
  public com.minecolonies.api.inventory.container.ContainerCitizenInventory(int, net.minecraft.world.entity.player.Inventory, int, int);
  public net.minecraft.world.item.ItemStack quickMoveStack(net.minecraft.world.entity.player.Player, int);
  public boolean stillValid(net.minecraft.world.entity.player.Player);
  public java.lang.String getDisplayName();
  public java.util.Optional<? extends net.minecraft.world.entity.Entity> getEntity();
  public com.minecolonies.api.colony.ICitizen getCitizenData();
}
