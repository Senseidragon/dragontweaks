Compiled from "IEntityManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.IEntityManager {
  public abstract void registerCivilian(com.minecolonies.api.entity.citizen.AbstractCivilianEntity);
  public abstract void unregisterCivilian(com.minecolonies.api.entity.citizen.AbstractCivilianEntity);
  public abstract void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void sendPackets(java.util.Set<net.minecraft.server.level.ServerPlayer>, java.util.Set<net.minecraft.server.level.ServerPlayer>);
  public abstract java.util.Map<java.lang.Integer, com.minecolonies.api.colony.ICivilianData> getCivilianDataMap();
  public abstract <T extends com.minecolonies.api.colony.ICivilianData> T getCivilian(int);
  public abstract <T extends com.minecolonies.api.colony.ICivilianData> T spawnOrCreateCivilian(T, net.minecraft.world.level.Level, java.util.List<net.minecraft.core.BlockPos>, boolean);
  public abstract com.minecolonies.api.colony.ICivilianData createAndRegisterCivilianData();
  public abstract void removeCivilian(com.minecolonies.api.colony.ICivilianData);
  public abstract void markDirty();
  public abstract void clearDirty();
  public abstract void onColonyTick(com.minecolonies.api.colony.IColony);
}
