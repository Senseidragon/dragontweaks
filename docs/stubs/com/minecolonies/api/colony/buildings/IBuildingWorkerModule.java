Compiled from "IBuildingWorkerModule.java"
public interface com.minecolonies.api.colony.buildings.IBuildingWorkerModule {
  public abstract com.minecolonies.api.colony.jobs.IJob<?> createJob(com.minecolonies.api.colony.ICitizenData);
  public abstract boolean canWorkDuringTheRain();
  public abstract com.minecolonies.api.entity.citizen.Skill getPrimarySkill();
  public abstract com.minecolonies.api.entity.citizen.Skill getSecondarySkill();
  public abstract com.minecolonies.api.colony.jobs.registry.JobEntry getJobEntry();
}
