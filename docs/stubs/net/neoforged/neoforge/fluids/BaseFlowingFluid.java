Compiled from "BaseFlowingFluid.java"
public abstract class net.neoforged.neoforge.fluids.BaseFlowingFluid extends net.minecraft.world.level.material.FlowingFluid {
  public net.neoforged.neoforge.fluids.FluidType getFluidType();
  public net.minecraft.world.level.material.Fluid getFlowing();
  public net.minecraft.world.level.material.Fluid getSource();
  public boolean canConvertToSource(net.minecraft.world.level.material.FluidState, net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public net.minecraft.world.item.Item getBucket();
  public int getTickDelay(net.minecraft.world.level.LevelReader);
  public boolean isSame(net.minecraft.world.level.material.Fluid);
  public java.util.Optional<net.minecraft.sounds.SoundEvent> getPickupSound();
}
