Compiled from "IPlayerExtension.java"
public interface net.neoforged.neoforge.common.extensions.IPlayerExtension {
  public default boolean isCloseEnough(net.minecraft.world.entity.Entity, double);
  public default java.util.OptionalInt openMenu(net.minecraft.world.MenuProvider, net.minecraft.core.BlockPos);
  public default java.util.OptionalInt openMenu(net.minecraft.world.MenuProvider, java.util.function.Consumer<net.minecraft.network.RegistryFriendlyByteBuf>);
  public default boolean mayFly();
  public default boolean isFakePlayer();
}
