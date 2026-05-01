Compiled from "IBlockGetterExtension.java"
public interface net.neoforged.neoforge.common.extensions.IBlockGetterExtension {
  public default net.neoforged.neoforge.common.world.AuxiliaryLightManager getAuxLightManager(net.minecraft.core.BlockPos);
  public default net.neoforged.neoforge.common.world.AuxiliaryLightManager getAuxLightManager(net.minecraft.world.level.ChunkPos);
  public default net.neoforged.neoforge.client.model.data.ModelData getModelData(net.minecraft.core.BlockPos);
}
