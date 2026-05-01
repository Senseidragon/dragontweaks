Compiled from "IPersistentModule.java"
public interface com.minecolonies.api.colony.buildings.modules.IPersistentModule extends com.minecolonies.api.colony.buildings.modules.IBuildingModule {
  public default void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public default void serializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
}
