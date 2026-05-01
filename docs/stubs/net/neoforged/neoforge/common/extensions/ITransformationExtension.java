Compiled from "ITransformationExtension.java"
public interface net.neoforged.neoforge.common.extensions.ITransformationExtension {
  public default boolean isIdentity();
  public default void transformPosition(org.joml.Vector4f);
  public default void transformNormal(org.joml.Vector3f);
  public default net.minecraft.core.Direction rotateTransform(net.minecraft.core.Direction);
  public default com.mojang.math.Transformation blockCenterToCorner();
  public default com.mojang.math.Transformation blockCornerToCenter();
  public default com.mojang.math.Transformation applyOrigin(org.joml.Vector3f);
}
