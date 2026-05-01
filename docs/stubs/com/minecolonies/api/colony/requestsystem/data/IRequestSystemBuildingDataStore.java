Compiled from "IRequestSystemBuildingDataStore.java"
public interface com.minecolonies.api.colony.requestsystem.data.IRequestSystemBuildingDataStore extends com.minecolonies.api.colony.requestsystem.data.IDataStore {
  public abstract java.util.Map<com.google.common.reflect.TypeToken<?>, java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>>> getOpenRequestsByRequestableType();
  public abstract java.util.Map<java.lang.Integer, java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>>> getOpenRequestsByCitizen();
  public abstract java.util.Map<java.lang.Integer, java.util.Collection<com.minecolonies.api.colony.requestsystem.token.IToken<?>>> getCompletedRequestsByCitizen();
  public abstract java.util.Map<com.minecolonies.api.colony.requestsystem.token.IToken<?>, java.lang.Integer> getCitizensByRequest();
  public abstract void moveToSyncCitizen(com.minecolonies.api.colony.ICitizenData, com.minecolonies.api.colony.requestsystem.request.IRequest<?>);
}
