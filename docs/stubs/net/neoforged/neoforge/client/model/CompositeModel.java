Compiled from "CompositeModel.java"
public class net.neoforged.neoforge.client.model.CompositeModel implements net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry<net.neoforged.neoforge.client.model.CompositeModel> {
  public net.neoforged.neoforge.client.model.CompositeModel(com.google.common.collect.ImmutableMap<java.lang.String, net.minecraft.client.renderer.block.model.BlockModel>, com.google.common.collect.ImmutableList<java.lang.String>);
  public net.minecraft.client.resources.model.BakedModel bake(net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext, net.minecraft.client.resources.model.ModelBaker, java.util.function.Function<net.minecraft.client.resources.model.Material, net.minecraft.client.renderer.texture.TextureAtlasSprite>, net.minecraft.client.resources.model.ModelState, net.minecraft.client.renderer.block.model.ItemOverrides);
  public void resolveParents(java.util.function.Function<net.minecraft.resources.ResourceLocation, net.minecraft.client.resources.model.UnbakedModel>, net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext);
  public java.util.Set<java.lang.String> getConfigurableComponentNames();
}
