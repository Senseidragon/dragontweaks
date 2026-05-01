Compiled from "TimeBasedHappinessModifier.java"
public final class com.minecolonies.api.entity.citizen.happiness.TimeBasedHappinessModifier extends com.minecolonies.api.entity.citizen.happiness.AbstractHappinessModifier implements com.minecolonies.api.entity.citizen.happiness.ITimeBasedHappinessModifier {
  public com.minecolonies.api.entity.citizen.happiness.TimeBasedHappinessModifier(java.lang.String, double, com.minecolonies.api.entity.citizen.happiness.IHappinessSupplierWrapper, com.minecolonies.api.util.Tuple<java.lang.Integer, java.lang.Double>...);
  public com.minecolonies.api.entity.citizen.happiness.TimeBasedHappinessModifier(java.lang.String, double, com.minecolonies.api.entity.citizen.happiness.IHappinessSupplierWrapper, java.util.function.BiPredicate<com.minecolonies.api.entity.citizen.happiness.TimeBasedHappinessModifier, com.minecolonies.api.colony.ICitizenData>, com.minecolonies.api.util.Tuple<java.lang.Integer, java.lang.Double>...);
  public com.minecolonies.api.entity.citizen.happiness.TimeBasedHappinessModifier();
  public double getFactor(com.minecolonies.api.colony.ICitizenData);
  public void reset();
  public int getDays();
  public void dayEnd(com.minecolonies.api.colony.ICitizenData);
  public void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, boolean);
  public void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, boolean);
}
