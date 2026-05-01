Compiled from "CreateFluidSourceEvent.java"
public class net.neoforged.neoforge.event.level.block.CreateFluidSourceEvent extends net.neoforged.neoforge.event.level.BlockEvent {
  public net.neoforged.neoforge.event.level.block.CreateFluidSourceEvent(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public net.minecraft.world.level.Level getLevel();
  public net.minecraft.world.level.material.FluidState getFluidState();
  public boolean getVanillaResult();
  public boolean canConvert();
  public void setCanConvert(boolean);
  public net.minecraft.world.level.LevelAccessor getLevel();
}
