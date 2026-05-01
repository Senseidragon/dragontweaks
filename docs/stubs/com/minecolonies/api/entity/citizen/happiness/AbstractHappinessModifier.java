Compiled from "AbstractHappinessModifier.java"
public abstract class com.minecolonies.api.entity.citizen.happiness.AbstractHappinessModifier implements com.minecolonies.api.entity.citizen.happiness.IHappinessModifier {
  public java.lang.String id;
  public com.minecolonies.api.entity.citizen.happiness.AbstractHappinessModifier(java.lang.String, double, com.minecolonies.api.entity.citizen.happiness.IHappinessSupplierWrapper);
  public double getFactor(com.minecolonies.api.colony.ICitizenData);
  public com.minecolonies.api.entity.citizen.happiness.AbstractHappinessModifier();
  public java.lang.String getId();
  public void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, boolean);
  public void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, boolean);
  public double getWeight();
}
