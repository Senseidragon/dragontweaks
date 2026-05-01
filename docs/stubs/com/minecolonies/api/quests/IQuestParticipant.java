Compiled from "IQuestParticipant.java"
public interface com.minecolonies.api.quests.IQuestParticipant {
  public abstract void addQuestParticipation(com.minecolonies.api.quests.IQuestInstance);
  public abstract void onQuestDeletion(net.minecraft.resources.ResourceLocation);
  public abstract boolean isParticipantOfQuest(net.minecraft.resources.ResourceLocation);
  public abstract void openDialogue(com.minecolonies.api.quests.IQuestInstance, int);
  public abstract java.lang.String getName();
}
