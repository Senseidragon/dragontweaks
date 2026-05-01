Compiled from "IGlobalResearchBranch.java"
public interface com.minecolonies.api.research.IGlobalResearchBranch {
  public abstract net.minecraft.network.chat.contents.TranslatableContents getName();
  public abstract net.minecraft.network.chat.contents.TranslatableContents getSubtitle();
  public abstract int getBaseTime(int);
  public abstract double getHoursTime(int);
  public abstract int getSortOrder();
  public abstract com.minecolonies.api.research.ResearchBranchType getType();
  public abstract boolean getHidden();
  public abstract net.minecraft.nbt.CompoundTag writeToNBT();
}
