Compiled from "IQuestTriggerTemplate.java"
public interface com.minecolonies.api.quests.IQuestTriggerTemplate {
  public abstract com.minecolonies.api.quests.ITriggerReturnData canTriggerQuest(com.minecolonies.api.colony.IColony);
  public default com.minecolonies.api.quests.ITriggerReturnData canTriggerQuest(net.minecraft.resources.ResourceLocation, com.minecolonies.api.colony.IColony);
  public static boolean matchNbt(net.minecraft.nbt.Tag, com.google.gson.JsonElement);
  public static boolean matchNbt(net.minecraft.nbt.Tag, com.google.gson.JsonElement, int);
}
