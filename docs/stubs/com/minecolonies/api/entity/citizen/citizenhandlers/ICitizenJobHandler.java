Compiled from "ICitizenJobHandler.java"
public interface com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenJobHandler {
  public abstract void setModelDependingOnJob(com.minecolonies.api.colony.jobs.IJob<?>);
  public abstract void onJobChanged(com.minecolonies.api.colony.jobs.IJob<?>);
  public abstract <J extends com.minecolonies.api.colony.jobs.IJob<?>> J getColonyJob(java.lang.Class<J>);
  public abstract com.minecolonies.api.colony.jobs.IJob<?> getColonyJob();
  public abstract boolean shouldRunAvoidance();
  public abstract void setWorkAI(com.minecolonies.api.entity.ai.ITickingStateAI);
  public abstract com.minecolonies.api.entity.ai.ITickingStateAI getWorkAI();
}
