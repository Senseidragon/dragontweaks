Compiled from "IQuestDeliveryObjective.java"
public interface com.minecolonies.api.quests.IQuestDeliveryObjective extends com.minecolonies.api.quests.IDialogueObjectiveTemplate {
  public abstract boolean hasItem(net.minecraft.world.entity.player.Player, com.minecolonies.api.quests.IQuestInstance);
  public abstract boolean tryDiscountItem(net.minecraft.world.entity.player.Player, com.minecolonies.api.quests.IQuestInstance);
  public abstract com.minecolonies.api.quests.IDialogueObjectiveTemplate$DialogueElement getReadyDialogueTree();
}
