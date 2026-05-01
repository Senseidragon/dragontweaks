Compiled from "IBakedModelExtension.java"
public interface net.neoforged.neoforge.client.extensions.IBakedModelExtension {
  public default java.util.List<net.minecraft.client.renderer.block.model.BakedQuad> getQuads(net.minecraft.world.level.block.state.BlockState, net.minecraft.core.Direction, net.minecraft.util.RandomSource, net.neoforged.neoforge.client.model.data.ModelData, net.minecraft.client.renderer.RenderType);
  public default net.neoforged.neoforge.common.util.TriState useAmbientOcclusion(net.minecraft.world.level.block.state.BlockState, net.neoforged.neoforge.client.model.data.ModelData, net.minecraft.client.renderer.RenderType);
  public default net.minecraft.client.resources.model.BakedModel applyTransform(net.minecraft.world.item.ItemDisplayContext, com.mojang.blaze3d.vertex.PoseStack, boolean);
  public default net.neoforged.neoforge.client.model.data.ModelData getModelData(net.minecraft.world.level.BlockAndTintGetter, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.neoforged.neoforge.client.model.data.ModelData);
  public default net.minecraft.client.renderer.texture.TextureAtlasSprite getParticleIcon(net.neoforged.neoforge.client.model.data.ModelData);
  public default net.neoforged.neoforge.client.ChunkRenderTypeSet getRenderTypes(net.minecraft.world.level.block.state.BlockState, net.minecraft.util.RandomSource, net.neoforged.neoforge.client.model.data.ModelData);
  public default java.util.List<net.minecraft.client.renderer.RenderType> getRenderTypes(net.minecraft.world.item.ItemStack, boolean);
  public default java.util.List<net.minecraft.client.resources.model.BakedModel> getRenderPasses(net.minecraft.world.item.ItemStack, boolean);
}
