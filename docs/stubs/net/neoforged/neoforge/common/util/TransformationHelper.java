Compiled from "TransformationHelper.java"
public final class net.neoforged.neoforge.common.util.TransformationHelper {
  public net.neoforged.neoforge.common.util.TransformationHelper();
  public static org.joml.Quaternionf quatFromXYZ(org.joml.Vector3f, boolean);
  public static org.joml.Quaternionf quatFromXYZ(float[], boolean);
  public static org.joml.Quaternionf quatFromXYZ(float, float, float, boolean);
  public static org.joml.Quaternionf makeQuaternion(float[]);
  public static org.joml.Vector3f lerp(org.joml.Vector3f, org.joml.Vector3f, float);
  public static org.joml.Quaternionf slerp(org.joml.Quaternionfc, org.joml.Quaternionfc, float);
  public static com.mojang.math.Transformation slerp(com.mojang.math.Transformation, com.mojang.math.Transformation, float);
  public static boolean epsilonEquals(org.joml.Vector4f, org.joml.Vector4f, float);
}
