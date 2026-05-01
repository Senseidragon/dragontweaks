Compiled from "ShapeUtil.java"
public class com.minecolonies.api.util.ShapeUtil {
  public com.minecolonies.api.util.ShapeUtil();
  public static double max(net.minecraft.world.phys.shapes.VoxelShape, net.minecraft.core.Direction$Axis);
  public static double min(net.minecraft.world.phys.shapes.VoxelShape, net.minecraft.core.Direction$Axis);
  public static boolean isEmpty(net.minecraft.world.phys.shapes.VoxelShape);
  public static double getStartY(net.minecraft.world.phys.shapes.VoxelShape, double);
  public static double getEndY(net.minecraft.world.phys.shapes.VoxelShape, double);
  public static boolean hasCollision(net.minecraft.world.level.BlockGetter, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public static boolean hasCollision(net.minecraft.world.level.BlockGetter, int, int, int, net.minecraft.world.level.block.state.BlockState);
  public static boolean hasCollision(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.phys.shapes.VoxelShape);
}
