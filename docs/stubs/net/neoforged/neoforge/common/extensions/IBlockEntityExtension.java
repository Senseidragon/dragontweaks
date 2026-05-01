Compiled from "IBlockEntityExtension.java"
public interface net.neoforged.neoforge.common.extensions.IBlockEntityExtension {
  public default void onDataPacket(net.minecraft.network.Connection, net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket, net.minecraft.core.HolderLookup$Provider);
  public default void handleUpdateTag(net.minecraft.nbt.CompoundTag, net.minecraft.core.HolderLookup$Provider);
  public abstract net.minecraft.nbt.CompoundTag getPersistentData();
  public default void onChunkUnloaded();
  public default void onLoad();
  public default void requestModelDataUpdate();
  public default net.neoforged.neoforge.client.model.data.ModelData getModelData();
  public default boolean hasCustomOutlineRendering(net.minecraft.world.entity.player.Player);
  public default void invalidateCapabilities();
}
