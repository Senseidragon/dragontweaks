Compiled from "ColonyConnectionNode.java"
public class com.minecolonies.api.colony.connections.ColonyConnectionNode {
  public com.minecolonies.api.colony.connections.ColonyConnectionNode(net.minecraft.core.BlockPos);
  public void alterPreviousNode(net.minecraft.core.BlockPos);
  public void alterNextNode(net.minecraft.core.BlockPos);
  public net.minecraft.core.BlockPos getPosition();
  public net.minecraft.core.BlockPos getPreviousNode();
  public net.minecraft.core.BlockPos getNextNode();
  public int getTargetColonyId();
  public void setTargetColonyId(int);
  public net.minecraft.nbt.CompoundTag write();
  public void read(net.minecraft.nbt.CompoundTag);
  public boolean hasNextNode();
}
