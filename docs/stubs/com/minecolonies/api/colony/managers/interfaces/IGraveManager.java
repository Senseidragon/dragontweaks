Compiled from "IGraveManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.IGraveManager {
  public abstract void read(net.minecraft.nbt.CompoundTag);
  public abstract void write(net.minecraft.nbt.CompoundTag);
  public abstract void onColonyTick(com.minecolonies.api.colony.IColony);
  public abstract boolean reserveGrave(net.minecraft.core.BlockPos);
  public abstract void unReserveGrave(net.minecraft.core.BlockPos);
  public abstract net.minecraft.core.BlockPos reserveNextFreeGrave();
  public abstract net.minecraft.core.BlockPos createCitizenGrave(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, com.minecolonies.api.colony.ICitizenData);
  public abstract java.util.Map<net.minecraft.core.BlockPos, java.lang.Boolean> getGraves();
  public abstract boolean addNewGrave(net.minecraft.core.BlockPos);
  public abstract void removeGrave(net.minecraft.core.BlockPos);
}
