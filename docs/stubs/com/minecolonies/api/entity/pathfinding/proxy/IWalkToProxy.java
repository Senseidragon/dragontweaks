Compiled from "IWalkToProxy.java"
public interface com.minecolonies.api.entity.pathfinding.proxy.IWalkToProxy {
  public abstract boolean walkToBlock(net.minecraft.core.BlockPos, int);
  public abstract boolean walkToBlock(net.minecraft.core.BlockPos, int, boolean);
  public abstract java.util.Set<net.minecraft.core.BlockPos> getWayPoints();
  public abstract boolean careAboutY();
  public abstract net.minecraft.core.BlockPos getSpecializedProxy(net.minecraft.core.BlockPos, double);
  public abstract java.util.List<net.minecraft.core.BlockPos> getProxyList();
  public abstract void addToProxyList(net.minecraft.core.BlockPos);
  public abstract boolean isLivingAtSiteWithMove(net.minecraft.world.entity.Mob, int, int, int, int);
  public abstract net.minecraft.world.entity.Mob getEntity();
  public abstract net.minecraft.core.BlockPos getCurrentProxy();
  public abstract void reset();
}
