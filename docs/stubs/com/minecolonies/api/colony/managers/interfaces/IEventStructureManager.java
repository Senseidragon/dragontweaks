Compiled from "IEventStructureManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.IEventStructureManager {
  public abstract boolean spawnTemporaryStructure(com.ldtteam.structurize.blueprints.v1.Blueprint, net.minecraft.core.BlockPos, int);
  public abstract void loadBackupForEvent(int);
  public abstract void readFromNBT(net.minecraft.nbt.CompoundTag);
  public abstract void writeToNBT(net.minecraft.nbt.CompoundTag);
}
