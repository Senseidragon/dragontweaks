Compiled from "IFluidTank.java"
public interface net.neoforged.neoforge.fluids.IFluidTank {
  public abstract net.neoforged.neoforge.fluids.FluidStack getFluid();
  public abstract int getFluidAmount();
  public abstract int getCapacity();
  public abstract boolean isFluidValid(net.neoforged.neoforge.fluids.FluidStack);
  public abstract int fill(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public abstract net.neoforged.neoforge.fluids.FluidStack drain(int, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public abstract net.neoforged.neoforge.fluids.FluidStack drain(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
}
