Compiled from "ICitizenMournHandler.java"
public interface com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenMournHandler {
  public abstract void read(net.minecraft.nbt.CompoundTag);
  public abstract void write(net.minecraft.nbt.CompoundTag);
  public abstract void addDeceasedCitizen(java.lang.String);
  public abstract java.util.Set<java.lang.String> getDeceasedCitizens();
  public abstract void removeDeceasedCitizen(java.lang.String);
  public abstract void clearDeceasedCitizen();
  public abstract boolean shouldMourn();
  public abstract boolean isMourning();
  public abstract void setMourning(boolean);
}
