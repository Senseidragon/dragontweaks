Compiled from "IMenuTypeExtension.java"
public interface net.neoforged.neoforge.common.extensions.IMenuTypeExtension<T> {
  public static <T extends net.minecraft.world.inventory.AbstractContainerMenu> net.minecraft.world.inventory.MenuType<T> create(net.neoforged.neoforge.network.IContainerFactory<T>);
  public abstract T create(int, net.minecraft.world.entity.player.Inventory, net.minecraft.network.RegistryFriendlyByteBuf);
}
