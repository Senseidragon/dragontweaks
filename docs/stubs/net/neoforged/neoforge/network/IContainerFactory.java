Compiled from "IContainerFactory.java"
public interface net.neoforged.neoforge.network.IContainerFactory<T extends net.minecraft.world.inventory.AbstractContainerMenu> extends net.minecraft.world.inventory.MenuType$MenuSupplier<T> {
  public abstract T create(int, net.minecraft.world.entity.player.Inventory, net.minecraft.network.RegistryFriendlyByteBuf);
  public default T create(int, net.minecraft.world.entity.player.Inventory);
}
