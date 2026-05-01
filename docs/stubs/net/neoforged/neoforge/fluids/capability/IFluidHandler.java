Compiled from "IFluidHandler.java"
public interface net.neoforged.neoforge.fluids.capability.IFluidHandler {
  public abstract int getTanks();
  public abstract net.neoforged.neoforge.fluids.FluidStack getFluidInTank(int);
  public abstract int getTankCapacity(int);
  public abstract boolean isFluidValid(int, net.neoforged.neoforge.fluids.FluidStack);
  public abstract int fill(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public abstract net.neoforged.neoforge.fluids.FluidStack drain(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public abstract net.neoforged.neoforge.fluids.FluidStack drain(int, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
}
