Compiled from "IRenderable.java"
public interface net.neoforged.neoforge.client.model.renderable.IRenderable<T> {
  public abstract void render(com.mojang.blaze3d.vertex.PoseStack, net.minecraft.client.renderer.MultiBufferSource, net.neoforged.neoforge.client.model.renderable.ITextureRenderTypeLookup, int, int, float, T);
  public default net.neoforged.neoforge.client.model.renderable.IRenderable<net.minecraft.util.Unit> withContext(T);
}
