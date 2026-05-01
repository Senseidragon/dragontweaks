Compiled from "ICitizenManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.ICitizenManager extends com.minecolonies.api.colony.managers.interfaces.IEntityManager {
  public abstract void spawnOrCreateCitizen();
  public default com.minecolonies.api.colony.ICitizenData spawnOrCreateCitizen(com.minecolonies.api.colony.ICitizenData, net.minecraft.world.level.Level);
  public default com.minecolonies.api.colony.ICitizenData spawnOrCreateCitizen(com.minecolonies.api.colony.ICitizenData, net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public abstract com.minecolonies.api.colony.ICitizenData getJoblessCitizen();
  public abstract void calculateMaxCitizens();
  public abstract com.minecolonies.api.colony.ICitizenData createAndRegisterCivilianData();
  public abstract com.minecolonies.api.colony.ICitizenData resurrectCivilianData(net.minecraft.nbt.CompoundTag, boolean, net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public abstract java.util.List<com.minecolonies.api.colony.ICitizenData> getCitizens();
  public abstract int getMaxCitizens();
  public abstract int getPotentialMaxCitizens();
  public abstract double maxCitizensFromResearch();
  public abstract int getCurrentCitizenCount();
  public abstract void setMaxCitizens(int);
  public abstract void setPotentialMaxCitizens(int);
  public abstract void checkCitizensForHappiness();
  public abstract boolean tickCitizenData(int);
  public abstract void updateCitizenMourn(com.minecolonies.api.colony.ICitizenData, boolean);
  public abstract void updateCitizenSleep(boolean);
  public abstract com.minecolonies.api.colony.ICitizenData getRandomCitizen();
  public abstract void injectModifier(com.minecolonies.api.entity.citizen.happiness.IHappinessModifier);
  public abstract void onCitizenSleep();
  public abstract com.minecolonies.api.colony.ICitizenData getCivilian(int);
  public abstract void onWakeUp();
  public abstract void afterBuildingLoad();
  public abstract void onFlagChange();
  public default com.minecolonies.api.colony.ICivilianData createAndRegisterCivilianData();
  public default com.minecolonies.api.colony.ICivilianData getCivilian(int);
}
