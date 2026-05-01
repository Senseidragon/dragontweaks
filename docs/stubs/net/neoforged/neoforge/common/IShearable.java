Compiled from "IShearable.java"
public interface net.neoforged.neoforge.common.IShearable {
  public default boolean isShearable(net.minecraft.world.entity.player.Player, net.minecraft.world.item.ItemStack, net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public default java.util.List<net.minecraft.world.item.ItemStack> onSheared(net.minecraft.world.entity.player.Player, net.minecraft.world.item.ItemStack, net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public default void spawnShearedDrop(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.item.ItemStack);
}
