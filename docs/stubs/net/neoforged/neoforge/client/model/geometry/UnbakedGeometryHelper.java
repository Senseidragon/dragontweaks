Compiled from "UnbakedGeometryHelper.java"
public class net.neoforged.neoforge.client.model.geometry.UnbakedGeometryHelper {
  public net.neoforged.neoforge.client.model.geometry.UnbakedGeometryHelper();
  public static net.minecraft.client.resources.model.Material resolveDirtyMaterial(java.lang.String, net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext);
  public static net.minecraft.client.resources.model.BakedModel bake(net.minecraft.client.renderer.block.model.BlockModel, net.minecraft.client.resources.model.ModelBaker, net.minecraft.client.renderer.block.model.BlockModel, java.util.function.Function<net.minecraft.client.resources.model.Material, net.minecraft.client.renderer.texture.TextureAtlasSprite>, net.minecraft.client.resources.model.ModelState, boolean);
  public static java.util.List<net.minecraft.client.renderer.block.model.BlockElement> createUnbakedItemElements(int, net.minecraft.client.renderer.texture.TextureAtlasSprite);
  public static java.util.List<net.minecraft.client.renderer.block.model.BlockElement> createUnbakedItemElements(int, net.minecraft.client.renderer.texture.TextureAtlasSprite, net.neoforged.neoforge.client.model.ExtraFaceData);
  public static java.util.List<net.minecraft.client.renderer.block.model.BlockElement> createUnbakedItemMaskElements(int, net.minecraft.client.renderer.texture.TextureAtlasSprite);
  public static java.util.List<net.minecraft.client.renderer.block.model.BlockElement> createUnbakedItemMaskElements(int, net.minecraft.client.renderer.texture.TextureAtlasSprite, net.neoforged.neoforge.client.model.ExtraFaceData);
  public static void bakeElements(net.neoforged.neoforge.client.model.IModelBuilder<?>, java.util.List<net.minecraft.client.renderer.block.model.BlockElement>, java.util.function.Function<net.minecraft.client.resources.model.Material, net.minecraft.client.renderer.texture.TextureAtlasSprite>, net.minecraft.client.resources.model.ModelState);
  public static java.util.List<net.minecraft.client.renderer.block.model.BakedQuad> bakeElements(java.util.List<net.minecraft.client.renderer.block.model.BlockElement>, java.util.function.Function<net.minecraft.client.resources.model.Material, net.minecraft.client.renderer.texture.TextureAtlasSprite>, net.minecraft.client.resources.model.ModelState);
  public static net.minecraft.client.renderer.block.model.BakedQuad bakeElementFace(net.minecraft.client.renderer.block.model.BlockElement, net.minecraft.client.renderer.block.model.BlockElementFace, net.minecraft.client.renderer.texture.TextureAtlasSprite, net.minecraft.core.Direction, net.minecraft.client.resources.model.ModelState);
  public static net.neoforged.neoforge.client.model.IQuadTransformer applyRootTransform(net.minecraft.client.resources.model.ModelState, com.mojang.math.Transformation);
  public static net.minecraft.client.resources.model.ModelState composeRootTransformIntoModelState(net.minecraft.client.resources.model.ModelState, com.mojang.math.Transformation);
}
