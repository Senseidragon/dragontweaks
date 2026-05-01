Compiled from "IUnbakedGeometry.java"
public interface net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry<T extends net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry<T>> {
  public abstract net.minecraft.client.resources.model.BakedModel bake(net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext, net.minecraft.client.resources.model.ModelBaker, java.util.function.Function<net.minecraft.client.resources.model.Material, net.minecraft.client.renderer.texture.TextureAtlasSprite>, net.minecraft.client.resources.model.ModelState, net.minecraft.client.renderer.block.model.ItemOverrides);
  public default void resolveParents(java.util.function.Function<net.minecraft.resources.ResourceLocation, net.minecraft.client.resources.model.UnbakedModel>, net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext);
  public default java.util.Set<java.lang.String> getConfigurableComponentNames();
}
