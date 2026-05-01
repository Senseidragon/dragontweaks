Compiled from "IQuestObjectiveTemplate.java"
public interface com.minecolonies.api.quests.IQuestObjectiveTemplate {
  public abstract com.minecolonies.api.quests.IObjectiveInstance startObjective(com.minecolonies.api.quests.IQuestInstance);
  public default void onCancellation(com.minecolonies.api.quests.IQuestInstance);
  public default void onWorldLoad(com.minecolonies.api.quests.IQuestInstance);
  public abstract net.minecraft.network.chat.Component getProgressText(com.minecolonies.api.quests.IQuestInstance, net.minecraft.network.chat.Style);
  public default com.minecolonies.api.quests.IObjectiveInstance createObjectiveInstance();
  public abstract java.util.List<java.lang.Integer> getRewardUnlocks();
  public abstract int getTarget();
}
