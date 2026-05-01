Compiled from "EnergyStorage.java"
public class net.neoforged.neoforge.energy.EnergyStorage implements net.neoforged.neoforge.energy.IEnergyStorage, net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.Tag> {
  public net.neoforged.neoforge.energy.EnergyStorage(int);
  public net.neoforged.neoforge.energy.EnergyStorage(int, int);
  public net.neoforged.neoforge.energy.EnergyStorage(int, int, int);
  public net.neoforged.neoforge.energy.EnergyStorage(int, int, int, int);
  public int receiveEnergy(int, boolean);
  public int extractEnergy(int, boolean);
  public int getEnergyStored();
  public int getMaxEnergyStored();
  public boolean canExtract();
  public boolean canReceive();
  public net.minecraft.nbt.Tag serializeNBT(net.minecraft.core.HolderLookup$Provider);
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.Tag);
}
