Compiled from "FluidBucketWrapper.java"
public class net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper implements net.neoforged.neoforge.fluids.capability.IFluidHandlerItem {
  public net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper(net.minecraft.world.item.ItemStack);
  public net.minecraft.world.item.ItemStack getContainer();
  public boolean canFillFluidType(net.neoforged.neoforge.fluids.FluidStack);
  public net.neoforged.neoforge.fluids.FluidStack getFluid();
  public int getTanks();
  public net.neoforged.neoforge.fluids.FluidStack getFluidInTank(int);
  public int getTankCapacity(int);
  public boolean isFluidValid(int, net.neoforged.neoforge.fluids.FluidStack);
  public int fill(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public net.neoforged.neoforge.fluids.FluidStack drain(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public net.neoforged.neoforge.fluids.FluidStack drain(int, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
}
