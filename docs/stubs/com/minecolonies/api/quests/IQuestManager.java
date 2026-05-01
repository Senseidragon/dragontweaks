Compiled from "IQuestManager.java"
public interface com.minecolonies.api.quests.IQuestManager extends net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public static final java.util.Map<net.minecraft.resources.ResourceLocation, com.minecolonies.api.quests.IQuestTemplate> GLOBAL_SERVER_QUESTS;
  public abstract boolean attemptAcceptQuest(net.minecraft.resources.ResourceLocation, net.minecraft.world.entity.player.Player);
  public abstract void completeQuest(net.minecraft.resources.ResourceLocation);
  public abstract void onColonyTick();
  public abstract void deleteQuest(net.minecraft.resources.ResourceLocation);
  public abstract com.minecolonies.api.quests.IQuestInstance getAvailableOrInProgressQuest(net.minecraft.resources.ResourceLocation);
  public abstract void onWorldLoad();
  public abstract void unlockQuest(net.minecraft.resources.ResourceLocation);
  public abstract boolean isUnlocked(net.minecraft.resources.ResourceLocation);
  public abstract void alterReputation(double);
  public abstract double getReputation();
  public abstract void markDirty();
  public abstract java.util.List<com.minecolonies.api.quests.IQuestInstance> getAvailableQuests();
  public abstract java.util.List<com.minecolonies.api.quests.IQuestInstance> getInProgressQuests();
  public abstract java.util.List<com.minecolonies.api.quests.FinishedQuest> getFinishedQuests();
  public abstract void injectAvailableQuest(com.minecolonies.api.quests.IQuestInstance);
  public abstract void serialize(net.minecraft.network.RegistryFriendlyByteBuf, boolean);
  public abstract void deserialize(net.minecraft.network.RegistryFriendlyByteBuf);
}
