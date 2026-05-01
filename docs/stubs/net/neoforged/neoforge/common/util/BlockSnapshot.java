Compiled from "BlockSnapshot.java"
public class net.neoforged.neoforge.common.util.BlockSnapshot {
  public static net.neoforged.neoforge.common.util.BlockSnapshot create(net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level>, net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos, int);
  public static net.neoforged.neoforge.common.util.BlockSnapshot create(net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level>, net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos);
  public net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level> getDimension();
  public net.minecraft.core.BlockPos getPos();
  public int getFlags();
  public net.minecraft.nbt.CompoundTag getTag();
  public net.minecraft.world.level.block.state.BlockState getState();
  public net.minecraft.world.level.LevelAccessor getLevel();
  public net.minecraft.world.level.block.state.BlockState getCurrentState();
  public net.minecraft.world.level.block.entity.BlockEntity recreateBlockEntity(net.minecraft.core.HolderLookup$Provider);
  public boolean restoreToLocation(net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos, int);
  public boolean restore(int);
  public boolean restore();
  public boolean restoreBlockEntity(net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos);
  public boolean equals(java.lang.Object);
  public int hashCode();
  public java.lang.String toString();
}
