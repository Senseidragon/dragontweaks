Compiled from "IColonyPackageManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.IColonyPackageManager {
  public abstract int getLastContactInHours();
  public abstract void setLastContactInHours(int);
  public abstract java.util.Set<net.minecraft.server.level.ServerPlayer> getCloseSubscribers();
  public abstract void updateSubscribers();
  public abstract void updateAwayTime();
  public abstract void sendColonyViewPackets();
  public abstract void sendPermissionsPackets();
  public abstract void sendWorkOrderPackets();
  public abstract void setDirty();
  public abstract void addCloseSubscriber(net.minecraft.server.level.ServerPlayer);
  public abstract void addImportantColonyPlayer(net.minecraft.server.level.ServerPlayer);
  public abstract void removeImportantColonyPlayer(net.minecraft.server.level.ServerPlayer);
  public abstract void removeCloseSubscriber(net.minecraft.server.level.ServerPlayer);
  public abstract java.util.Set<net.minecraft.server.level.ServerPlayer> getImportantColonyPlayers();
}
