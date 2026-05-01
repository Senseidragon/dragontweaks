Compiled from "ICitizenHappinessHandler.java"
public interface com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenHappinessHandler {
  public abstract void addModifier(com.minecolonies.api.entity.citizen.happiness.IHappinessModifier);
  public abstract void resetModifier(java.lang.String);
  public abstract com.minecolonies.api.entity.citizen.happiness.IHappinessModifier getModifier(java.lang.String);
  public abstract void processDailyHappiness(com.minecolonies.api.colony.ICitizenData);
  public abstract double getHappiness(com.minecolonies.api.colony.IColony, com.minecolonies.api.colony.ICitizenData);
  public abstract void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, boolean);
  public abstract void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, boolean);
  public abstract java.util.List<java.lang.String> getModifiers();
}
