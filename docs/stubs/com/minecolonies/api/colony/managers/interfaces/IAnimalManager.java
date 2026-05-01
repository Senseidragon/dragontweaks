Compiled from "IAnimalManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.IAnimalManager {
  public abstract int getCurrentAnimalCount();
  public abstract void registerAnimal(com.minecolonies.api.colony.managers.interfaces.IManagedAnimal<? extends net.minecraft.world.entity.animal.Animal>);
  public abstract com.minecolonies.api.colony.IAnimalData getAnimal(int);
  public abstract java.util.List<com.minecolonies.api.colony.IAnimalData> getAnimals();
  public abstract com.minecolonies.api.colony.IAnimalData createAndRegisterAnimalData(com.minecolonies.api.colony.managers.interfaces.IManagedAnimal<? extends net.minecraft.world.entity.animal.Animal>);
  public abstract void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void onColonyTick(com.minecolonies.api.colony.IColony);
  public abstract boolean tickAnimalData(int);
  public abstract void markDirty();
  public abstract void clearDirty();
  public abstract void sendPackets(java.util.Set<net.minecraft.server.level.ServerPlayer>, java.util.Set<net.minecraft.server.level.ServerPlayer>);
}
