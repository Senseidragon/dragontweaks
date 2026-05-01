Compiled from "ICitizenDataManager.java"
public interface com.minecolonies.api.colony.ICitizenDataManager {
  public static com.minecolonies.api.colony.ICitizenDataManager getInstance();
  public abstract com.minecolonies.api.colony.ICitizenData createFromNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, com.minecolonies.api.colony.IColony);
  public abstract com.minecolonies.api.colony.ICitizenDataView createFromNetworkData(int, net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.colony.IColonyView);
}
