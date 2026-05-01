Compiled from "IModelBakerExtension.java"
public interface net.neoforged.neoforge.client.extensions.IModelBakerExtension {
  public abstract net.minecraft.client.resources.model.UnbakedModel getTopLevelModel(net.minecraft.client.resources.model.ModelResourceLocation);
  public abstract net.minecraft.client.resources.model.BakedModel bake(net.minecraft.resources.ResourceLocation, net.minecraft.client.resources.model.ModelState, java.util.function.Function<net.minecraft.client.resources.model.Material, net.minecraft.client.renderer.texture.TextureAtlasSprite>);
  public abstract net.minecraft.client.resources.model.BakedModel bakeUncached(net.minecraft.client.resources.model.UnbakedModel, net.minecraft.client.resources.model.ModelState, java.util.function.Function<net.minecraft.client.resources.model.Material, net.minecraft.client.renderer.texture.TextureAtlasSprite>);
  public abstract java.util.function.Function<net.minecraft.client.resources.model.Material, net.minecraft.client.renderer.texture.TextureAtlasSprite> getModelTextureGetter();
}
