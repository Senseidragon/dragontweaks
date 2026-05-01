Compiled from "AbstractTileEntityNamedGrave.java"
public class com.minecolonies.api.tileentities.AbstractTileEntityNamedGrave extends net.minecraft.world.level.block.entity.BlockEntity {
  public static final net.minecraft.world.level.block.state.properties.DirectionProperty FACING;
  public com.minecolonies.api.tileentities.AbstractTileEntityNamedGrave(net.minecraft.world.level.block.entity.BlockEntityType<?>, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState);
  public java.util.ArrayList<java.lang.String> getTextLines();
  public void setTextLines(java.util.ArrayList<java.lang.String>);
  public void loadAdditional(net.minecraft.nbt.CompoundTag, net.minecraft.core.HolderLookup$Provider);
  public void saveAdditional(net.minecraft.nbt.CompoundTag, net.minecraft.core.HolderLookup$Provider);
  public net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket getUpdatePacket();
  public net.minecraft.nbt.CompoundTag getUpdateTag(net.minecraft.core.HolderLookup$Provider);
  public void onDataPacket(net.minecraft.network.Connection, net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket, net.minecraft.core.HolderLookup$Provider);
  public void handleUpdateTag(net.minecraft.nbt.CompoundTag, net.minecraft.core.HolderLookup$Provider);
  public void setChanged();
  public net.minecraft.network.protocol.Packet getUpdatePacket();
}
