Compiled from "IEventManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.IEventManager {
  public abstract void addEvent(com.minecolonies.api.colony.colonyEvents.IColonyEvent);
  public abstract int getAndTakeNextEventID();
  public abstract void registerEntity(net.minecraft.world.entity.Entity, int);
  public abstract void unregisterEntity(net.minecraft.world.entity.Entity, int);
  public abstract void onEntityDeath(net.minecraft.world.entity.LivingEntity, int);
  public abstract void onNightFall();
  public abstract void onTileEntityBreak(int, net.minecraft.world.level.block.entity.BlockEntity);
  public abstract void onColonyTick(com.minecolonies.api.colony.IColony);
  public abstract com.minecolonies.api.colony.colonyEvents.IColonyEvent getEventByID(int);
  public abstract java.util.Map<java.lang.Integer, com.minecolonies.api.colony.colonyEvents.IColonyEvent> getEvents();
  public abstract void readFromNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void writeToNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract com.minecolonies.api.colony.managers.interfaces.IEventStructureManager getStructureManager();
}
