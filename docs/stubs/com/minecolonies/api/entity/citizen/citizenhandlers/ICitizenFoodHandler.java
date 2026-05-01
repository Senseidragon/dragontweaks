Compiled from "ICitizenFoodHandler.java"
public interface com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenFoodHandler {
  public abstract void addLastEaten(net.minecraft.world.item.Item);
  public abstract net.minecraft.world.item.Item getLastEaten();
  public abstract int checkLastEaten(net.minecraft.world.item.Item);
  public abstract com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenFoodHandler$CitizenFoodStats getFoodHappinessStats();
  public abstract void read(net.minecraft.nbt.CompoundTag);
  public abstract void write(net.minecraft.nbt.CompoundTag);
  public abstract double getDiseaseModifier(double);
  public abstract boolean hasFullFoodHistory();
  public abstract com.google.common.collect.ImmutableList<net.minecraft.world.item.Item> getLastEatenFoods();
}
