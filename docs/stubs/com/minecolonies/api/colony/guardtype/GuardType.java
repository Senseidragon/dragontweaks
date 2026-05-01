Compiled from "GuardType.java"
public class com.minecolonies.api.colony.guardtype.GuardType {
  public com.minecolonies.api.colony.guardtype.GuardType(java.util.function.Supplier<com.minecolonies.api.colony.jobs.registry.JobEntry>, java.lang.String, java.lang.String, com.minecolonies.api.entity.citizen.Skill, com.minecolonies.api.entity.citizen.Skill, java.lang.String, java.lang.Class<com.minecolonies.api.colony.jobs.IJob<?>>, net.minecraft.resources.ResourceLocation);
  public java.util.function.Supplier<com.minecolonies.api.colony.jobs.registry.JobEntry> getJobEntry();
  public java.lang.String getJobTranslationKey();
  public java.lang.String getButtonTranslationKey();
  public com.minecolonies.api.entity.citizen.Skill getPrimarySkill();
  public com.minecolonies.api.entity.citizen.Skill getSecondarySkill();
  public java.lang.String getWorkerSoundName();
  public boolean isInstance(com.minecolonies.api.colony.jobs.IJob<?>);
}
