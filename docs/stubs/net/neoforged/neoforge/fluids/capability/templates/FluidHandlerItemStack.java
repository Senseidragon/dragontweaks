Compiled from "FluidHandlerItemStack.java"
public class net.neoforged.neoforge.fluids.capability.templates.FluidHandlerItemStack implements net.neoforged.neoforge.fluids.capability.IFluidHandlerItem {
  public net.neoforged.neoforge.fluids.capability.templates.FluidHandlerItemStack(java.util.function.Supplier<net.minecraft.core.component.DataComponentType<net.neoforged.neoforge.fluids.SimpleFluidContent>>, net.minecraft.world.item.ItemStack, int);
  public net.minecraft.world.item.ItemStack getContainer();
  public net.neoforged.neoforge.fluids.FluidStack getFluid();
  public int getTanks();
  public net.neoforged.neoforge.fluids.FluidStack getFluidInTank(int);
  public int getTankCapacity(int);
  public boolean isFluidValid(int, net.neoforged.neoforge.fluids.FluidStack);
  public int fill(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public net.neoforged.neoforge.fluids.FluidStack drain(net.neoforged.neoforge.fluids.FluidStack, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public net.neoforged.neoforge.fluids.FluidStack drain(int, net.neoforged.neoforge.fluids.capability.IFluidHandler$FluidAction);
  public boolean canFillFluidType(net.neoforged.neoforge.fluids.FluidStack);
  public boolean canDrainFluidType(net.neoforged.neoforge.fluids.FluidStack);
}
