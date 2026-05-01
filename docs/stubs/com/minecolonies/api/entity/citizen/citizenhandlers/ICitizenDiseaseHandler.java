Compiled from "ICitizenDiseaseHandler.java"
public interface com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenDiseaseHandler {
  public abstract void update(int);
  public abstract boolean isSick();
  public abstract void write(net.minecraft.nbt.CompoundTag);
  public abstract void read(net.minecraft.nbt.CompoundTag);
  public abstract com.minecolonies.core.datalistener.model.Disease getDisease();
  public abstract void cure();
  public abstract void onCollission(com.minecolonies.api.colony.ICitizenData);
  public abstract boolean isHurt();
  public abstract boolean sleepsAtHospital();
  public abstract void setSleepsAtHospital(boolean);
  public abstract boolean setDisease(com.minecolonies.core.datalistener.model.Disease);
}
