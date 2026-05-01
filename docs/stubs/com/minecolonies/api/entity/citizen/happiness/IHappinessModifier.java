Compiled from "IHappinessModifier.java"
public interface com.minecolonies.api.entity.citizen.happiness.IHappinessModifier {
  public abstract java.lang.String getId();
  public abstract double getFactor(com.minecolonies.api.colony.ICitizenData);
  public abstract double getWeight();
  public abstract void read(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, boolean);
  public abstract void write(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, boolean);
}
