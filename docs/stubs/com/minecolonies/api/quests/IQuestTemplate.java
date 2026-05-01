Compiled from "IQuestTemplate.java"
public interface com.minecolonies.api.quests.IQuestTemplate {
  public abstract com.minecolonies.api.quests.IQuestInstance attemptStart(com.minecolonies.api.colony.IColony);
  public abstract int getQuestTimeout();
  public abstract int getMaxOccurrence();
  public abstract void unlockQuestRewards(com.minecolonies.api.colony.IColony, net.minecraft.world.entity.player.Player, com.minecolonies.api.quests.IQuestInstance, java.util.List<java.lang.Integer>);
  public abstract com.minecolonies.api.quests.IQuestObjectiveTemplate getObjective(int);
  public abstract int getObjectiveCount();
  public abstract net.minecraft.network.chat.Component getName();
  public abstract java.util.List<net.minecraft.resources.ResourceLocation> getParents();
}
