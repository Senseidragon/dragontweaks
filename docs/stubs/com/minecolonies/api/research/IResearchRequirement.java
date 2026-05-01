Compiled from "IResearchRequirement.java"
public interface com.minecolonies.api.research.IResearchRequirement {
  public abstract com.minecolonies.api.research.ModResearchRequirements$ResearchRequirementEntry getRegistryEntry();
  public abstract net.minecraft.network.chat.MutableComponent getDesc();
  public abstract boolean isFulfilled(com.minecolonies.api.colony.IColony);
  public abstract net.minecraft.nbt.CompoundTag writeToNBT();
}
