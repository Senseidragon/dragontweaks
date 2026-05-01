Compiled from "IRaiderManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.IRaiderManager {
  public abstract boolean canHaveRaiderEvents();
  public abstract boolean willRaidTonight();
  public abstract void setCanHaveRaiderEvents(boolean);
  public abstract void setRaidNextNight(com.minecolonies.api.colony.managers.interfaces.IRaiderManager$RaidSettings);
  public abstract boolean areSpiesEnabled();
  public abstract void setSpiesEnabled(boolean);
  public abstract com.minecolonies.api.colony.managers.interfaces.IRaiderManager$RaidSpawnResult raiderEvent(com.minecolonies.api.colony.managers.interfaces.IRaiderManager$RaidSettings);
  public abstract net.minecraft.core.BlockPos calculateSpawnLocation();
  public abstract java.util.List<net.minecraft.core.BlockPos> getLastSpawnPoints();
  public abstract int calculateRaiderAmount(int);
  public abstract boolean isRaided();
  public abstract void onNightFall();
  public abstract int getNightsSinceLastRaid();
  public abstract void setNightsSinceLastRaid(int);
  public abstract boolean canRaid();
  public abstract int getColonyRaidLevel();
  public abstract net.minecraft.core.BlockPos getRandomBuilding();
  public abstract double getRaidDifficultyModifier();
  public abstract void onLostCitizen(com.minecolonies.api.colony.ICitizenData);
  public abstract void write(net.minecraft.nbt.CompoundTag);
  public abstract void read(net.minecraft.nbt.CompoundTag);
  public abstract int getLostCitizen();
  public abstract void onRaiderDeath(com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesRaider);
  public abstract void onRaidEventFinished(com.minecolonies.api.colony.colonyEvents.IColonyRaidEvent);
  public abstract void setPassThroughRaid();
}
