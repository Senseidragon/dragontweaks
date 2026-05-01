Compiled from "ResearchResearchRequirement.java"
public class com.minecolonies.api.research.requirements.ResearchResearchRequirement implements com.minecolonies.api.research.IResearchRequirement {
  public static final java.lang.String RESEARCH_REQUIRED_RESEARCH_PROP;
  public com.minecolonies.api.research.requirements.ResearchResearchRequirement(net.minecraft.nbt.CompoundTag);
  public com.minecolonies.api.research.requirements.ResearchResearchRequirement(com.google.gson.JsonObject);
  public net.minecraft.resources.ResourceLocation getResearchId();
  public com.minecolonies.api.research.ModResearchRequirements$ResearchRequirementEntry getRegistryEntry();
  public net.minecraft.network.chat.MutableComponent getDesc();
  public boolean isFulfilled(com.minecolonies.api.colony.IColony);
  public net.minecraft.nbt.CompoundTag writeToNBT();
}
