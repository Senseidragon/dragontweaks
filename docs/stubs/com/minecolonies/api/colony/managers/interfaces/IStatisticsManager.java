Compiled from "IStatisticsManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.IStatisticsManager {
  public abstract void increment(java.lang.String, int);
  public abstract void incrementBy(java.lang.String, int, int);
  public abstract int getStatTotal(java.lang.String);
  public abstract int getStatsInPeriod(java.lang.String, int, int);
  public abstract void serialize(net.minecraft.network.RegistryFriendlyByteBuf, boolean);
  public abstract void deserialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract void readFromNBT(net.minecraft.nbt.CompoundTag);
  public abstract void writeToNBT(net.minecraft.nbt.CompoundTag);
  public abstract java.util.Set<java.lang.String> getStatTypes();
  public abstract java.util.Set<java.util.Map$Entry<java.lang.String, it.unimi.dsi.fastutil.ints.Int2IntLinkedOpenHashMap>> getStatEntries();
  public abstract void clear();
  public static void aggregateStats(com.minecolonies.api.colony.managers.interfaces.IStatisticsManager, com.minecolonies.api.colony.managers.interfaces.IStatisticsManager);
}
