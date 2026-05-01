Compiled from "ILocalResearchTree.java"
public interface com.minecolonies.api.research.ILocalResearchTree {
  public abstract com.minecolonies.api.research.ILocalResearch getResearch(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation);
  public abstract void addResearch(net.minecraft.resources.ResourceLocation, com.minecolonies.api.research.ILocalResearch);
  public abstract boolean branchFinishedHighestLevel(net.minecraft.resources.ResourceLocation);
  public abstract java.util.List<com.minecolonies.api.research.ILocalResearch> getResearchInProgress();
  public abstract boolean hasCompletedResearch(net.minecraft.resources.ResourceLocation);
  public abstract void finishResearch(net.minecraft.resources.ResourceLocation);
  public abstract void attemptBeginResearch(net.minecraft.world.entity.player.Player, com.minecolonies.api.colony.IColony, com.minecolonies.core.colony.buildings.workerbuildings.BuildingUniversity, com.minecolonies.api.research.IGlobalResearch);
  public abstract void attemptResetResearch(net.minecraft.world.entity.player.Player, com.minecolonies.api.colony.IColony, com.minecolonies.api.research.ILocalResearch);
  public abstract void writeToNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void readFromNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, com.minecolonies.api.research.IResearchEffectManager);
  public abstract java.util.List<net.minecraft.resources.ResourceLocation> getCompletedList();
  public abstract boolean isComplete(net.minecraft.resources.ResourceLocation);
}
