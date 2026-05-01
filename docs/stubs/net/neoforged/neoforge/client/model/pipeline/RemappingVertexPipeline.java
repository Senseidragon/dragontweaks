Compiled from "RemappingVertexPipeline.java"
public class net.neoforged.neoforge.client.model.pipeline.RemappingVertexPipeline implements com.mojang.blaze3d.vertex.VertexConsumer {
  public net.neoforged.neoforge.client.model.pipeline.RemappingVertexPipeline(com.mojang.blaze3d.vertex.VertexConsumer, com.mojang.blaze3d.vertex.VertexFormat);
  public com.mojang.blaze3d.vertex.VertexConsumer addVertex(float, float, float);
  public com.mojang.blaze3d.vertex.VertexConsumer setNormal(float, float, float);
  public com.mojang.blaze3d.vertex.VertexConsumer setColor(int, int, int, int);
  public com.mojang.blaze3d.vertex.VertexConsumer setUv(float, float);
  public com.mojang.blaze3d.vertex.VertexConsumer setUv1(int, int);
  public com.mojang.blaze3d.vertex.VertexConsumer setUv2(int, int);
  public com.mojang.blaze3d.vertex.VertexConsumer misc(com.mojang.blaze3d.vertex.VertexFormatElement, int...);
  public void endVertex();
}
