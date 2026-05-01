Compiled from "IFluidStateExtension.java"
public interface net.neoforged.neoforge.common.extensions.IFluidStateExtension {
  public default float getExplosionResistance(net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos, net.minecraft.world.level.Explosion);
  public default net.neoforged.neoforge.fluids.FluidType getFluidType();
  public default boolean move(net.minecraft.world.entity.LivingEntity, net.minecraft.world.phys.Vec3, double);
  public default boolean canConvertToSource(net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public default boolean supportsBoating(net.minecraft.world.entity.vehicle.Boat);
  public default net.minecraft.world.level.pathfinder.PathType getBlockPathType(net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos, net.minecraft.world.entity.Mob, boolean);
  public default net.minecraft.world.level.pathfinder.PathType getAdjacentBlockPathType(net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos, net.minecraft.world.entity.Mob, net.minecraft.world.level.pathfinder.PathType);
  public default boolean canHydrate(net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.core.BlockPos);
  public default boolean canExtinguish(net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos);
}
