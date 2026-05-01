Compiled from "ICitizenSkillHandler.java"
public interface com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenSkillHandler {
  public abstract void init(int);
  public abstract void init(com.minecolonies.api.colony.IColony, com.minecolonies.api.colony.ICitizenData, com.minecolonies.api.colony.ICitizenData, java.util.Random);
  public abstract net.minecraft.nbt.CompoundTag write();
  public abstract void read(net.minecraft.nbt.CompoundTag);
  public abstract boolean tryLevelUpIntelligence(java.util.Random, double, com.minecolonies.api.colony.ICitizenData);
  public abstract int getLevel(com.minecolonies.api.entity.citizen.Skill);
  public abstract void incrementLevel(com.minecolonies.api.entity.citizen.Skill, int);
  public abstract void addXpToSkill(com.minecolonies.api.entity.citizen.Skill, double, com.minecolonies.api.colony.ICitizenData);
  public abstract void removeXpFromSkill(com.minecolonies.api.entity.citizen.Skill, double, com.minecolonies.api.colony.ICitizenData);
  public abstract void levelUp(com.minecolonies.api.colony.ICitizenData);
  public abstract double getTotalXP();
  public abstract java.util.Map<com.minecolonies.api.entity.citizen.Skill, com.minecolonies.core.entity.citizen.citizenhandlers.CitizenSkillHandler$SkillData> getSkills();
}
