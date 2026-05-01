Compiled from "ILevelExtension.java"
public interface net.neoforged.neoforge.common.extensions.ILevelExtension {
  public static final java.lang.String TRANSLATION_PREFIX;
  public abstract double getMaxEntityRadius();
  public abstract double increaseMaxEntityRadius(double);
  public default java.util.Collection<net.neoforged.neoforge.entity.PartEntity<?>> getPartEntities();
  public default net.neoforged.neoforge.client.model.data.ModelDataManager getModelDataManager();
  public default <T, C> T getCapability(net.neoforged.neoforge.capabilities.BlockCapability<T, C>, net.minecraft.core.BlockPos, C);
  public default <T, C> T getCapability(net.neoforged.neoforge.capabilities.BlockCapability<T, C>, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.block.entity.BlockEntity, C);
  public default <T> T getCapability(net.neoforged.neoforge.capabilities.BlockCapability<T, java.lang.Void>, net.minecraft.core.BlockPos);
  public default <T> T getCapability(net.neoforged.neoforge.capabilities.BlockCapability<T, java.lang.Void>, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.block.entity.BlockEntity);
  public default void invalidateCapabilities(net.minecraft.core.BlockPos);
  public default void invalidateCapabilities(net.minecraft.world.level.ChunkPos);
  public default java.lang.String getDescriptionKey();
  public default net.minecraft.network.chat.Component getDescription();
}
