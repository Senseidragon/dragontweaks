Compiled from "IGraveData.java"
public interface com.minecolonies.api.colony.IGraveData {
  public abstract net.minecraft.nbt.CompoundTag getCitizenDataNBT();
  public abstract void setCitizenDataNBT(net.minecraft.nbt.CompoundTag);
  public abstract java.lang.String getCitizenName();
  public abstract void setCitizenName(java.lang.String);
  public abstract java.lang.String getCitizenJobName();
  public abstract void setCitizenJobName(java.lang.String);
  public abstract void read(net.minecraft.nbt.CompoundTag);
  public abstract net.minecraft.nbt.CompoundTag write();
}
