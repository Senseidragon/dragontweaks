Compiled from "BuildingResearchRequirement.java"
public class com.minecolonies.api.research.requirements.BuildingResearchRequirement implements com.minecolonies.api.research.IResearchRequirement {
  public com.minecolonies.api.research.requirements.BuildingResearchRequirement(net.minecraft.nbt.CompoundTag);
  public static net.minecraft.resources.ResourceLocation parseFallbackBuildingKey(java.lang.String);
  public com.minecolonies.api.research.requirements.BuildingResearchRequirement(com.google.gson.JsonObject);
  public net.minecraft.resources.ResourceLocation getBuilding();
  public int getBuildingLevel();
  public com.minecolonies.api.research.ModResearchRequirements$ResearchRequirementEntry getRegistryEntry();
  public net.minecraft.network.chat.MutableComponent getDesc();
  public boolean isFulfilled(com.minecolonies.api.colony.IColony);
  public net.minecraft.nbt.CompoundTag writeToNBT();
}
