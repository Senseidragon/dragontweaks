Compiled from "IServerColonySaveData.java"
public interface com.minecolonies.api.colony.savedata.IServerColonySaveData {
  public abstract com.minecolonies.api.colony.IColony createColony(net.minecraft.server.level.ServerLevel, java.lang.String, net.minecraft.core.BlockPos);
  public abstract void deleteColony(int);
  public abstract com.minecolonies.api.colony.IColony getColony(int);
  public abstract java.util.List<com.minecolonies.api.colony.IColony> getColonies();
  public abstract void addColony(com.minecolonies.api.colony.IColony);
  public abstract int getTopID();
  public abstract com.minecolonies.api.colony.savedata.IServerColonySaveData setOverworld(boolean);
  public static com.minecolonies.api.colony.savedata.IServerColonySaveData getOrComputeSaveData(net.minecraft.server.level.ServerLevel);
  public static com.minecolonies.api.colony.savedata.IServerColonySaveData getSaveData(net.minecraft.server.level.ServerLevel);
}
