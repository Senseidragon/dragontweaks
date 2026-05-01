Compiled from "IAnimalData.java"
public interface com.minecolonies.api.colony.IAnimalData extends net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public abstract int getId();
  public abstract java.util.UUID getUUID();
  public abstract void initEntityValues();
  public abstract java.util.Optional<com.minecolonies.api.colony.managers.interfaces.IManagedAnimal<? extends net.minecraft.world.entity.animal.Animal>> getManagedAnimal();
  public abstract void setManagedAnimal(com.minecolonies.api.colony.managers.interfaces.IManagedAnimal<? extends net.minecraft.world.entity.animal.Animal>);
  public abstract void clearDirty();
  public abstract boolean isDirty();
  public abstract void markDirty();
  public abstract void update(int);
  public abstract void serializeViewNetworkData(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract com.minecolonies.api.colony.buildings.IBuilding getHomeBuilding();
  public abstract void setHomeBuilding(com.minecolonies.api.colony.buildings.IBuilding);
  public abstract void onRemoveBuilding(com.minecolonies.api.colony.buildings.IBuilding);
  public abstract void setLastPosition(net.minecraft.core.BlockPos);
  public abstract net.minecraft.core.BlockPos getLastPosition();
  public abstract float getCombatCooldown();
  public abstract void setCombatCooldown(float);
}
