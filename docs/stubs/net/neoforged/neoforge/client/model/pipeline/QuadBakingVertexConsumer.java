Compiled from "QuadBakingVertexConsumer.java"
public class net.neoforged.neoforge.client.model.pipeline.QuadBakingVertexConsumer implements com.mojang.blaze3d.vertex.VertexConsumer {
  public net.neoforged.neoforge.client.model.pipeline.QuadBakingVertexConsumer();
  public com.mojang.blaze3d.vertex.VertexConsumer addVertex(float, float, float);
  public com.mojang.blaze3d.vertex.VertexConsumer setNormal(float, float, float);
  public com.mojang.blaze3d.vertex.VertexConsumer setColor(int, int, int, int);
  public com.mojang.blaze3d.vertex.VertexConsumer setUv(float, float);
  public com.mojang.blaze3d.vertex.VertexConsumer setUv1(int, int);
  public com.mojang.blaze3d.vertex.VertexConsumer setUv2(int, int);
  public com.mojang.blaze3d.vertex.VertexConsumer misc(com.mojang.blaze3d.vertex.VertexFormatElement, int...);
  public void setTintIndex(int);
  public void setDirection(net.minecraft.core.Direction);
  public void setSprite(net.minecraft.client.renderer.texture.TextureAtlasSprite);
  public void setShade(boolean);
  public void setHasAmbientOcclusion(boolean);
  public net.minecraft.client.renderer.block.model.BakedQuad bakeQuad();
}
