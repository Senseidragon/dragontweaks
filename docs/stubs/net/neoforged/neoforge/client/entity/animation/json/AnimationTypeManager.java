Compiled from "AnimationTypeManager.java"
public final class net.neoforged.neoforge.client.entity.animation.json.AnimationTypeManager {
  public static net.neoforged.neoforge.client.entity.animation.AnimationTarget getTarget(net.minecraft.resources.ResourceLocation);
  public static net.minecraft.resources.ResourceLocation getTargetName(net.neoforged.neoforge.client.entity.animation.AnimationTarget);
  public static net.neoforged.neoforge.client.entity.animation.AnimationTarget getTargetFromChannelTarget(net.minecraft.client.animation.AnimationChannel$Target);
  public static com.mojang.serialization.Codec<net.minecraft.client.animation.Keyframe> getKeyframeCodec(net.neoforged.neoforge.client.entity.animation.AnimationTarget);
  public static net.minecraft.client.animation.AnimationChannel$Interpolation getInterpolation(net.minecraft.resources.ResourceLocation);
  public static net.minecraft.resources.ResourceLocation getInterpolationName(net.minecraft.client.animation.AnimationChannel$Interpolation);
  public static java.lang.String getTargetList();
  public static java.lang.String getInterpolationList();
  public static void init();
}
