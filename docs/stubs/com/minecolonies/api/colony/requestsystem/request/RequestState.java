Compiled from "RequestState.java"
public final class com.minecolonies.api.colony.requestsystem.request.RequestState extends java.lang.Enum<com.minecolonies.api.colony.requestsystem.request.RequestState> {
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState CREATED;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState REPORTED;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState ASSIGNING;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState ASSIGNED;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState IN_PROGRESS;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState RESOLVED;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState FOLLOWUP_IN_PROGRESS;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState COMPLETED;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState OVERRULED;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState CANCELLED;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState RECEIVED;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState FINALIZING;
  public static final com.minecolonies.api.colony.requestsystem.request.RequestState FAILED;
  public static com.minecolonies.api.colony.requestsystem.request.RequestState[] values();
  public static com.minecolonies.api.colony.requestsystem.request.RequestState valueOf(java.lang.String);
  public static com.minecolonies.api.colony.requestsystem.request.RequestState deserialize(net.minecraft.nbt.IntTag);
  public net.minecraft.nbt.IntTag serialize();
  public static com.minecolonies.api.colony.requestsystem.request.RequestState deserialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public void serialize(net.minecraft.network.RegistryFriendlyByteBuf);
}
