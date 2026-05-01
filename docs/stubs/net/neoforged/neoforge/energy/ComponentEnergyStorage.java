Compiled from "ComponentEnergyStorage.java"
public class net.neoforged.neoforge.energy.ComponentEnergyStorage implements net.neoforged.neoforge.energy.IEnergyStorage {
  public net.neoforged.neoforge.energy.ComponentEnergyStorage(net.neoforged.neoforge.common.MutableDataComponentHolder, net.minecraft.core.component.DataComponentType<java.lang.Integer>, int, int, int);
  public net.neoforged.neoforge.energy.ComponentEnergyStorage(net.neoforged.neoforge.common.MutableDataComponentHolder, net.minecraft.core.component.DataComponentType<java.lang.Integer>, int, int);
  public net.neoforged.neoforge.energy.ComponentEnergyStorage(net.neoforged.neoforge.common.MutableDataComponentHolder, net.minecraft.core.component.DataComponentType<java.lang.Integer>, int);
  public int receiveEnergy(int, boolean);
  public int extractEnergy(int, boolean);
  public int getEnergyStored();
  public int getMaxEnergyStored();
  public boolean canExtract();
  public boolean canReceive();
}
