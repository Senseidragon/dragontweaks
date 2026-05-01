Compiled from "IJobDataManager.java"
public interface com.minecolonies.api.colony.jobs.registry.IJobDataManager {
  public static com.minecolonies.api.colony.jobs.registry.IJobDataManager getInstance();
  public abstract com.minecolonies.api.colony.jobs.IJob<?> createFrom(com.minecolonies.api.colony.ICitizenData, net.minecraft.nbt.CompoundTag, net.minecraft.core.HolderLookup$Provider);
  public abstract com.minecolonies.api.colony.jobs.IJobView createViewFrom(com.minecolonies.api.colony.IColonyView, com.minecolonies.api.colony.ICitizenDataView, net.minecraft.network.RegistryFriendlyByteBuf);
}
