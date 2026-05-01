Compiled from "StaticHappinessSupplier.java"
public class com.minecolonies.api.entity.citizen.happiness.StaticHappinessSupplier implements com.minecolonies.api.entity.citizen.happiness.IHappinessSupplierWrapper {
  public com.minecolonies.api.entity.citizen.happiness.StaticHappinessSupplier(double);
  public com.minecolonies.api.entity.citizen.happiness.StaticHappinessSupplier();
  public net.minecraft.nbt.CompoundTag serializeNBT(net.minecraft.core.HolderLookup$Provider);
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public double getValue(com.minecolonies.api.colony.ICitizenData);
  public double getLastCachedValue();
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.Tag);
  public net.minecraft.nbt.Tag serializeNBT(net.minecraft.core.HolderLookup$Provider);
}
