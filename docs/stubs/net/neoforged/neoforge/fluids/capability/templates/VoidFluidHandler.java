Compiled from "VoidFluidHandler.java"
public class net.neoforged.neoforge.fluids.capability.templates.VoidFluidHandler implements net.neoforged.neoforge.fluids.capability.IFluidHandler {
  public static final net.neoforged.neoforge.fluids.capability.templates.VoidFluidHandler INSTANCE;
  public net.neoforged.neoforge.fluids.capability.templates.VoidFluidHandler();
  public int getTanks();
  public net.neoforged.neoforge.fluids.FluidStack getFluidInTank(int);
  public int getTankCapacity(int);
  public boolean isFluidValid(int, net.neoforged.neoforge.fluids.FluidStack);
  public int fill(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public net.neoforged.neoforge.fluids.FluidStack drain(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public net.neoforged.neoforge.fluids.FluidStack drain(int, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
}
