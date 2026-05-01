Compiled from "BakedModelWrapper.java"
public abstract class net.neoforged.neoforge.client.model.BakedModelWrapper<T extends net.minecraft.client.resources.model.BakedModel> implements net.minecraft.client.resources.model.BakedModel {
  public net.neoforged.neoforge.client.model.BakedModelWrapper(T);
  public java.util.List<net.minecraft.client.renderer.block.model.BakedQuad> getQuads(net.minecraft.world.level.block.state.BlockState, net.minecraft.core.Direction, net.minecraft.util.RandomSource);
  public boolean useAmbientOcclusion();
  public net.neoforged.neoforge.common.util.TriState useAmbientOcclusion(net.minecraft.world.level.block.state.BlockState, net.neoforged.neoforge.client.model.data.ModelData, net.minecraft.client.renderer.RenderType);
  public boolean isGui3d();
  public boolean usesBlockLight();
  public boolean isCustomRenderer();
  public net.minecraft.client.renderer.texture.TextureAtlasSprite getParticleIcon();
  public net.minecraft.client.renderer.block.model.ItemTransforms getTransforms();
  public net.minecraft.client.renderer.block.model.ItemOverrides getOverrides();
  public net.minecraft.client.resources.model.BakedModel applyTransform(net.minecraft.world.item.ItemDisplayContext, com.mojang.blaze3d.vertex.PoseStack, boolean);
  public net.minecraft.client.renderer.texture.TextureAtlasSprite getParticleIcon(net.neoforged.neoforge.client.model.data.ModelData);
  public java.util.List<net.minecraft.client.renderer.block.model.BakedQuad> getQuads(net.minecraft.world.level.block.state.BlockState, net.minecraft.core.Direction, net.minecraft.util.RandomSource, net.neoforged.neoforge.client.model.data.ModelData, net.minecraft.client.renderer.RenderType);
  public net.neoforged.neoforge.client.model.data.ModelData getModelData(net.minecraft.world.level.BlockAndTintGetter, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.neoforged.neoforge.client.model.data.ModelData);
  public net.neoforged.neoforge.client.ChunkRenderTypeSet getRenderTypes(net.minecraft.world.level.block.state.BlockState, net.minecraft.util.RandomSource, net.neoforged.neoforge.client.model.data.ModelData);
  public java.util.List<net.minecraft.client.renderer.RenderType> getRenderTypes(net.minecraft.world.item.ItemStack, boolean);
  public java.util.List<net.minecraft.client.resources.model.BakedModel> getRenderPasses(net.minecraft.world.item.ItemStack, boolean);
}
