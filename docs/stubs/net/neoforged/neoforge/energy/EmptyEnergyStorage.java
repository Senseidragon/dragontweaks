Compiled from "EmptyEnergyStorage.java"
public class net.neoforged.neoforge.energy.EmptyEnergyStorage implements net.neoforged.neoforge.energy.IEnergyStorage {
  public static final net.neoforged.neoforge.energy.EmptyEnergyStorage INSTANCE;
  public int receiveEnergy(int, boolean);
  public int extractEnergy(int, boolean);
  public int getEnergyStored();
  public int getMaxEnergyStored();
  public boolean canExtract();
  public boolean canReceive();
}
