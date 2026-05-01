Compiled from "IEnergyStorage.java"
public interface net.neoforged.neoforge.energy.IEnergyStorage {
  public abstract int receiveEnergy(int, boolean);
  public abstract int extractEnergy(int, boolean);
  public abstract int getEnergyStored();
  public abstract int getMaxEnergyStored();
  public abstract boolean canExtract();
  public abstract boolean canReceive();
}
