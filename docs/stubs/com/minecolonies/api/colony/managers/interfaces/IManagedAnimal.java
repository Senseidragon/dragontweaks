Compiled from "IManagedAnimal.java"
public interface com.minecolonies.api.colony.managers.interfaces.IManagedAnimal<T extends net.minecraft.world.entity.animal.Animal> {
  public abstract T getEntity();
  public abstract net.minecraft.network.syncher.EntityDataAccessor<java.lang.Integer> getColonyIdAccessor();
  public abstract net.minecraft.network.syncher.EntityDataAccessor<java.lang.Integer> getAnimalIdAccessor();
  public abstract int getManagedAnimalId();
  public abstract void setManagedAnimalId(int);
  public abstract int getColonyId();
  public abstract void setColonyId(int);
  public abstract com.minecolonies.api.colony.IAnimalData getAnimalData();
  public abstract com.minecolonies.api.colony.managers.interfaces.IAnimalDataView getAnimalDataView();
  public abstract void setAnimalData(com.minecolonies.api.colony.IAnimalData);
  public default int getOffsetTicks();
}
