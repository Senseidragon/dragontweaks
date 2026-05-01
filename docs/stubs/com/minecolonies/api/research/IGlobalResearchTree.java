Compiled from "IGlobalResearchTree.java"
public interface com.minecolonies.api.research.IGlobalResearchTree {
  public static com.minecolonies.api.research.IGlobalResearchTree getInstance();
  public abstract com.minecolonies.api.research.IGlobalResearch getResearch(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation);
  public abstract com.minecolonies.api.research.IGlobalResearch getResearch(net.minecraft.resources.ResourceLocation);
  public abstract java.util.List<com.minecolonies.api.research.IResearchEffect> getEffectsForResearch(net.minecraft.resources.ResourceLocation);
  public abstract boolean hasResearch(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation);
  public abstract boolean hasResearch(net.minecraft.resources.ResourceLocation);
  public abstract void addResearch(net.minecraft.resources.ResourceLocation, com.minecolonies.api.research.IGlobalResearch, boolean);
  public abstract void addBranchData(net.minecraft.resources.ResourceLocation, com.minecolonies.api.research.IGlobalResearchBranch);
  public abstract java.util.List<net.minecraft.resources.ResourceLocation> getBranches();
  public abstract com.minecolonies.api.research.IGlobalResearchBranch getBranchData(net.minecraft.resources.ResourceLocation);
  public abstract void reset();
  public abstract java.util.List<net.minecraft.resources.ResourceLocation> getPrimaryResearch(net.minecraft.resources.ResourceLocation);
  public abstract java.util.Set<com.minecolonies.api.research.IGlobalResearch> getAutostartResearches();
  public abstract java.util.List<com.minecolonies.api.crafting.ItemStorage> getResearchResetCosts(net.minecraft.core.HolderLookup$Provider);
  public abstract boolean hasResearchEffect(net.minecraft.resources.ResourceLocation);
  public abstract java.util.Set<com.minecolonies.api.research.IGlobalResearch> getResearchForEffect(net.minecraft.resources.ResourceLocation);
  public abstract boolean isResearchRequirementsFulfilled(java.util.List<com.minecolonies.api.research.IResearchRequirement>, com.minecolonies.api.colony.IColony);
  public abstract void handleGlobalResearchTreeMessage(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract void sendGlobalResearchTreePackets(net.minecraft.server.level.ServerPlayer);
}
