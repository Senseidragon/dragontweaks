Compiled from "ILocalResearch.java"
public interface com.minecolonies.api.research.ILocalResearch {
  public abstract int getProgress();
  public abstract net.minecraft.resources.ResourceLocation getId();
  public abstract com.minecolonies.api.research.util.ResearchState getState();
  public abstract net.minecraft.resources.ResourceLocation getBranch();
  public abstract int getDepth();
  public abstract void setState(com.minecolonies.api.research.util.ResearchState);
  public abstract void setProgress(int);
  public abstract boolean research(com.minecolonies.api.research.IResearchEffectManager, com.minecolonies.api.research.ILocalResearchTree);
}
