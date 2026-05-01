Compiled from "BucketPickupHandlerWrapper.java"
public class net.neoforged.neoforge.fluids.capability.wrappers.BucketPickupHandlerWrapper implements net.neoforged.neoforge.fluids.capability.IFluidHandler {
  public net.neoforged.neoforge.fluids.capability.wrappers.BucketPickupHandlerWrapper(net.minecraft.world.entity.player.Player, net.minecraft.world.level.block.BucketPickup, net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public int getTanks();
  public net.neoforged.neoforge.fluids.FluidStack getFluidInTank(int);
  public int getTankCapacity(int);
  public boolean isFluidValid(int, net.neoforged.neoforge.fluids.FluidStack);
  public int fill(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public net.neoforged.neoforge.fluids.FluidStack drain(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public net.neoforged.neoforge.fluids.FluidStack drain(int, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
}
