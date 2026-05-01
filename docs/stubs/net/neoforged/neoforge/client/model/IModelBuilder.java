Compiled from "IModelBuilder.java"
public interface net.neoforged.neoforge.client.model.IModelBuilder<T extends net.neoforged.neoforge.client.model.IModelBuilder<T>> {
  public static net.neoforged.neoforge.client.model.IModelBuilder<?> of(boolean, boolean, boolean, net.minecraft.client.renderer.block.model.ItemTransforms, net.minecraft.client.renderer.block.model.ItemOverrides, net.minecraft.client.renderer.texture.TextureAtlasSprite, net.neoforged.neoforge.client.RenderTypeGroup);
  public static net.neoforged.neoforge.client.model.IModelBuilder<?> collecting(java.util.List<net.minecraft.client.renderer.block.model.BakedQuad>);
  public abstract T addCulledFace(net.minecraft.core.Direction, net.minecraft.client.renderer.block.model.BakedQuad);
  public abstract T addUnculledFace(net.minecraft.client.renderer.block.model.BakedQuad);
  public abstract net.minecraft.client.resources.model.BakedModel build();
}
