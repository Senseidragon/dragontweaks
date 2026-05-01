Compiled from "BuildingAlternatesResearchRequirement.java"
public class com.minecolonies.api.research.requirements.BuildingAlternatesResearchRequirement implements com.minecolonies.api.research.IResearchRequirement {
  public com.minecolonies.api.research.requirements.BuildingAlternatesResearchRequirement(net.minecraft.nbt.CompoundTag);
  public com.minecolonies.api.research.requirements.BuildingAlternatesResearchRequirement(com.google.gson.JsonObject);
  public java.util.Set<net.minecraft.resources.ResourceLocation> getBuildings();
  public int getBuildingLevel();
  public com.minecolonies.api.research.ModResearchRequirements$ResearchRequirementEntry getRegistryEntry();
  public net.minecraft.network.chat.MutableComponent getDesc();
  public boolean isFulfilled(com.minecolonies.api.colony.IColony);
  public net.minecraft.nbt.CompoundTag writeToNBT();
}
