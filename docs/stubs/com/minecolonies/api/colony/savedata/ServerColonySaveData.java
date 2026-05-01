Compiled from "ServerColonySaveData.java"
public class com.minecolonies.api.colony.savedata.ServerColonySaveData extends net.minecraft.world.level.saveddata.SavedData implements com.minecolonies.api.colony.savedata.IServerColonySaveData {
  public static final java.lang.String NAME;
  public static final net.minecraft.world.level.saveddata.SavedData$Factory<com.minecolonies.api.colony.savedata.ServerColonySaveData> FACTORY;
  public net.minecraft.nbt.CompoundTag save(net.minecraft.nbt.CompoundTag, net.minecraft.core.HolderLookup$Provider);
  public com.minecolonies.api.colony.IColony createColony(net.minecraft.server.level.ServerLevel, java.lang.String, net.minecraft.core.BlockPos);
  public void deleteColony(int);
  public com.minecolonies.api.colony.IColony getColony(int);
  public java.util.List<com.minecolonies.api.colony.IColony> getColonies();
  public void addColony(com.minecolonies.api.colony.IColony);
  public int getTopID();
  public boolean isDirty();
  public com.minecolonies.api.colony.savedata.IServerColonySaveData setOverworld(boolean);
}
