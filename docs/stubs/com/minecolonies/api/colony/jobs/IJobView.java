Compiled from "IJobView.java"
public interface com.minecolonies.api.colony.jobs.IJobView {
  public abstract java.lang.String getName();
  public abstract java.util.Set<com.minecolonies.api.colony.requestsystem.token.IToken<?>> getAsyncRequests();
  public abstract void deserialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract com.minecolonies.api.colony.jobs.registry.JobEntry getEntry();
  public abstract void setEntry(com.minecolonies.api.colony.jobs.registry.JobEntry);
}
