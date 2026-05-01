Compiled from "IQuestInstance.java"
public interface com.minecolonies.api.quests.IQuestInstance extends net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public abstract void onStart(net.minecraft.world.entity.player.Player, com.minecolonies.api.colony.IColony);
  public abstract com.minecolonies.api.quests.IQuestGiver getQuestGiver();
  public abstract int getQuestGiverId();
  public abstract boolean isValid(com.minecolonies.api.colony.IColony);
  public abstract net.minecraft.resources.ResourceLocation getId();
  public abstract void onDeletion();
  public abstract com.minecolonies.api.quests.IObjectiveInstance advanceObjective(net.minecraft.world.entity.player.Player, int);
  public abstract void onCompletion();
  public abstract int getObjectiveIndex();
  public abstract com.minecolonies.api.quests.IQuestParticipant getParticipant(int);
  public abstract java.util.List<java.lang.Integer> getParticipants();
  public abstract int getQuestTarget();
  public abstract com.minecolonies.api.quests.IObjectiveInstance getCurrentObjectiveInstance();
  public abstract com.minecolonies.api.colony.IColony getColony();
  public abstract java.util.UUID getAssignedPlayer();
  public abstract void advanceObjective(net.minecraft.world.entity.player.Player);
  public abstract void onWorldLoad();
}
