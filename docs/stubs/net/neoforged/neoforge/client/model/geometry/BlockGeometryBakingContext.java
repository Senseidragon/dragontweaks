Compiled from "BlockGeometryBakingContext.java"
public class net.neoforged.neoforge.client.model.geometry.BlockGeometryBakingContext implements net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext {
  public final net.minecraft.client.renderer.block.model.BlockModel owner;
  public final net.neoforged.neoforge.client.model.geometry.BlockGeometryBakingContext$VisibilityData visibilityData;
  public net.neoforged.neoforge.client.model.geometry.BlockGeometryBakingContext(net.minecraft.client.renderer.block.model.BlockModel);
  public java.lang.String getModelName();
  public boolean hasCustomGeometry();
  public net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry<?> getCustomGeometry();
  public void setCustomGeometry(net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry<?>);
  public boolean isComponentVisible(java.lang.String, boolean);
  public boolean hasMaterial(java.lang.String);
  public net.minecraft.client.resources.model.Material getMaterial(java.lang.String);
  public boolean isGui3d();
  public boolean useBlockLight();
  public boolean useAmbientOcclusion();
  public net.minecraft.client.renderer.block.model.ItemTransforms getTransforms();
  public com.mojang.math.Transformation getRootTransform();
  public void setRootTransform(com.mojang.math.Transformation);
  public net.minecraft.resources.ResourceLocation getRenderTypeHint();
  public void setRenderTypeHint(net.minecraft.resources.ResourceLocation);
  public void setGui3d(boolean);
  public void copyFrom(net.neoforged.neoforge.client.model.geometry.BlockGeometryBakingContext);
  public net.minecraft.client.resources.model.BakedModel bake(net.minecraft.client.resources.model.ModelBaker, java.util.function.Function<net.minecraft.client.resources.model.Material, net.minecraft.client.renderer.texture.TextureAtlasSprite>, net.minecraft.client.resources.model.ModelState, net.minecraft.client.renderer.block.model.ItemOverrides);
}
