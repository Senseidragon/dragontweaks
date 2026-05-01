Compiled from "IVertexConsumerExtension.java"
public interface net.neoforged.neoforge.client.extensions.IVertexConsumerExtension {
  public default com.mojang.blaze3d.vertex.VertexConsumer misc(com.mojang.blaze3d.vertex.VertexFormatElement, int...);
  public default void putBulkData(com.mojang.blaze3d.vertex.PoseStack$Pose, net.minecraft.client.renderer.block.model.BakedQuad, float, float, float, float, int, int, boolean);
  public default int applyBakedLighting(int, java.nio.ByteBuffer);
  public default void applyBakedNormals(org.joml.Vector3f, java.nio.ByteBuffer, org.joml.Matrix3f);
}
