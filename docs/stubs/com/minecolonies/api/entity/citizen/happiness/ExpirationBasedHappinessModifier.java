Compiled from "ExpirationBasedHappinessModifier.java"
public final class com.minecolonies.api.entity.citizen.happiness.ExpirationBasedHappinessModifier extends com.minecolonies.api.entity.citizen.happiness.AbstractHappinessModifier implements com.minecolonies.api.entity.citizen.happiness.ITimeBasedHappinessModifier {
  public com.minecolonies.api.entity.citizen.happiness.ExpirationBasedHappinessModifier(java.lang.String, double, com.minecolonies.api.entity.citizen.happiness.IHappinessSupplierWrapper, int);
  public com.minecolonies.api.entity.citizen.happiness.ExpirationBasedHappinessModifier();
  public double getFactor(com.minecolonies.api.colony.ICitizenData);
  public void reset();
  public void dayEnd(com.minecolonies.api.colony.ICitizenData);
  public int getDays();
  public void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, boolean);
  public void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, boolean);
}
