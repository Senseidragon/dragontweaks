Compiled from "IBuildingWorker.java"
public interface com.minecolonies.api.colony.buildings.IBuildingWorker extends com.minecolonies.api.colony.buildings.IBuilding {
  public static final int WOOD_HUT_LEVEL;
  public abstract com.minecolonies.api.colony.jobs.IJob<?> createJob(com.minecolonies.api.colony.ICitizenData);
  public abstract boolean isItemStackInRequest(net.minecraft.world.item.ItemStack);
  public abstract void setHiringMode(com.minecolonies.api.colony.buildings.HiringMode);
  public abstract com.minecolonies.api.colony.buildings.HiringMode getHiringMode();
  public abstract java.util.List<net.neoforged.neoforge.items.IItemHandler> getHandlers();
  public abstract boolean assignCitizen(com.minecolonies.api.colony.ICitizenData);
  public abstract java.lang.String getJobName();
  public abstract int getMaxEquipmentLevel();
  public abstract boolean canWorkDuringTheRain();
  public abstract com.minecolonies.api.entity.citizen.Skill getPrimarySkill();
  public abstract com.minecolonies.api.entity.citizen.Skill getSecondarySkill();
  public abstract com.minecolonies.api.entity.citizen.Skill getRecipeImprovementSkill();
  public abstract boolean canEat(net.minecraft.world.item.ItemStack);
}
