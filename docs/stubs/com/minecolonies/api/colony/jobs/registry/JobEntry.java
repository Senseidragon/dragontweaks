Compiled from "JobEntry.java"
public final class com.minecolonies.api.colony.jobs.registry.JobEntry {
  public com.minecolonies.api.colony.jobs.IJob<?> produceJob(com.minecolonies.api.colony.ICitizenData);
  public java.lang.String getTranslationKey();
  public net.minecraft.resources.ResourceLocation getKey();
  public boolean equals(java.lang.Object);
  public int hashCode();
  public java.util.function.Supplier<java.util.function.BiFunction<com.minecolonies.api.colony.IColonyView, com.minecolonies.api.colony.ICitizenDataView, com.minecolonies.api.colony.jobs.IJobView>> getJobViewProducer();
}
