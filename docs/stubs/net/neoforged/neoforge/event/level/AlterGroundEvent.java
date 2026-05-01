Compiled from "AlterGroundEvent.java"
public class net.neoforged.neoforge.event.level.AlterGroundEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.level.AlterGroundEvent(net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator$Context, java.util.List<net.minecraft.core.BlockPos>, net.neoforged.neoforge.event.level.AlterGroundEvent$StateProvider);
  public net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator$Context getContext();
  public java.util.List<net.minecraft.core.BlockPos> getPositions();
  public net.neoforged.neoforge.event.level.AlterGroundEvent$StateProvider getStateProvider();
  public void setStateProvider(net.neoforged.neoforge.event.level.AlterGroundEvent$StateProvider);
}
