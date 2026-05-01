Compiled from "IBaseRailBlockExtension.java"
public interface net.neoforged.neoforge.common.extensions.IBaseRailBlockExtension {
  public abstract boolean isFlexibleRail(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos);
  public default boolean canMakeSlopes(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos);
  public abstract net.minecraft.world.level.block.state.properties.RailShape getRailDirection(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos, net.minecraft.world.entity.vehicle.AbstractMinecart);
  public default float getRailMaxSpeed(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.entity.vehicle.AbstractMinecart);
  public default void onMinecartPass(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.entity.vehicle.AbstractMinecart);
  public default boolean isValidRailShape(net.minecraft.world.level.block.state.properties.RailShape);
}
