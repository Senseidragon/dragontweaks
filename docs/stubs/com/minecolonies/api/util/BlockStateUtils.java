Compiled from "BlockStateUtils.java"
public class com.minecolonies.api.util.BlockStateUtils {
  public static boolean stateEqualsStateByBlockAndProp(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.block.state.BlockState, java.lang.String);
  public static boolean stateEqualsStateInPropertyByName(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.block.state.BlockState, java.lang.String);
  public static net.minecraft.world.level.block.state.properties.Property<?> getPropertyByNameFromState(net.minecraft.world.level.block.state.BlockState, java.lang.String);
  public static net.minecraft.world.level.block.state.properties.Property<?> getPropertyByName(java.util.Collection<net.minecraft.world.level.block.state.properties.Property<?>>, java.lang.String);
  public static <T extends java.lang.Comparable<T>> boolean stateEqualsStateWithoutProp(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.block.state.properties.Property<T>);
  public static boolean stateEqualsStateInBlockAndProp(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.block.state.BlockState);
}
