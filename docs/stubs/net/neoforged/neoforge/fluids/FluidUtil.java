Compiled from "FluidUtil.java"
public class net.neoforged.neoforge.fluids.FluidUtil {
  public static boolean interactWithFluidHandler(net.minecraft.world.entity.player.Player, net.minecraft.world.InteractionHand, net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.core.Direction);
  public static boolean interactWithFluidHandler(net.minecraft.world.entity.player.Player, net.minecraft.world.InteractionHand, net.neoforged.neoforge.fluids.capability.IFluidHandler);
  public static net.neoforged.neoforge.fluids.FluidActionResult tryFillContainer(net.minecraft.world.item.ItemStack, net.neoforged.neoforge.fluids.capability.IFluidHandler, int, net.minecraft.world.entity.player.Player, boolean);
  public static net.neoforged.neoforge.fluids.FluidActionResult tryEmptyContainer(net.minecraft.world.item.ItemStack, net.neoforged.neoforge.fluids.capability.IFluidHandler, int, net.minecraft.world.entity.player.Player, boolean);
  public static net.neoforged.neoforge.fluids.FluidActionResult tryFillContainerAndStow(net.minecraft.world.item.ItemStack, net.neoforged.neoforge.fluids.capability.IFluidHandler, net.neoforged.neoforge.items.IItemHandler, int, net.minecraft.world.entity.player.Player, boolean);
  public static net.neoforged.neoforge.fluids.FluidActionResult tryEmptyContainerAndStow(net.minecraft.world.item.ItemStack, net.neoforged.neoforge.fluids.capability.IFluidHandler, net.neoforged.neoforge.items.IItemHandler, int, net.minecraft.world.entity.player.Player, boolean);
  public static net.neoforged.neoforge.fluids.FluidStack tryFluidTransfer(net.neoforged.neoforge.fluids.capability.IFluidHandler, net.neoforged.neoforge.fluids.capability.IFluidHandler, int, boolean);
  public static net.neoforged.neoforge.fluids.FluidStack tryFluidTransfer(net.neoforged.neoforge.fluids.capability.IFluidHandler, net.neoforged.neoforge.fluids.capability.IFluidHandler, net.neoforged.neoforge.fluids.FluidStack, boolean);
  public static java.util.Optional<net.neoforged.neoforge.fluids.capability.IFluidHandlerItem> getFluidHandler(net.minecraft.world.item.ItemStack);
  public static java.util.Optional<net.neoforged.neoforge.fluids.FluidStack> getFluidContained(net.minecraft.world.item.ItemStack);
  public static java.util.Optional<net.neoforged.neoforge.fluids.capability.IFluidHandler> getFluidHandler(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.core.Direction);
  public static net.neoforged.neoforge.fluids.FluidActionResult tryPickUpFluid(net.minecraft.world.item.ItemStack, net.minecraft.world.entity.player.Player, net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.core.Direction);
  public static net.neoforged.neoforge.fluids.FluidActionResult tryPlaceFluid(net.minecraft.world.entity.player.Player, net.minecraft.world.level.Level, net.minecraft.world.InteractionHand, net.minecraft.core.BlockPos, net.minecraft.world.item.ItemStack, net.neoforged.neoforge.fluids.FluidStack);
  public static boolean tryPlaceFluid(net.minecraft.world.entity.player.Player, net.minecraft.world.level.Level, net.minecraft.world.InteractionHand, net.minecraft.core.BlockPos, net.neoforged.neoforge.fluids.capability.IFluidHandler, net.neoforged.neoforge.fluids.FluidStack);
  public static void destroyBlockOnFluidPlacement(net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public static net.minecraft.world.item.ItemStack getFilledBucket(net.neoforged.neoforge.fluids.FluidStack);
}
