Compiled from "FluidTank.java"
public class net.neoforged.neoforge.fluids.capability.templates.FluidTank implements net.neoforged.neoforge.fluids.capability.IFluidHandler,net.neoforged.neoforge.fluids.IFluidTank {
  public net.neoforged.neoforge.fluids.capability.templates.FluidTank(int);
  public net.neoforged.neoforge.fluids.capability.templates.FluidTank(int, java.util.function.Predicate<net.neoforged.neoforge.fluids.FluidStack>);
  public net.neoforged.neoforge.fluids.capability.templates.FluidTank setCapacity(int);
  public net.neoforged.neoforge.fluids.capability.templates.FluidTank setValidator(java.util.function.Predicate<net.neoforged.neoforge.fluids.FluidStack>);
  public boolean isFluidValid(net.neoforged.neoforge.fluids.FluidStack);
  public int getCapacity();
  public net.neoforged.neoforge.fluids.FluidStack getFluid();
  public int getFluidAmount();
  public net.neoforged.neoforge.fluids.capability.templates.FluidTank readFromNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public net.minecraft.nbt.CompoundTag writeToNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public int getTanks();
  public net.neoforged.neoforge.fluids.FluidStack getFluidInTank(int);
  public int getTankCapacity(int);
  public boolean isFluidValid(int, net.neoforged.neoforge.fluids.FluidStack);
  public int fill(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public net.neoforged.neoforge.fluids.FluidStack drain(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public net.neoforged.neoforge.fluids.FluidStack drain(int, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public void setFluid(net.neoforged.neoforge.fluids.FluidStack);
  public boolean isEmpty();
  public int getSpace();
}
