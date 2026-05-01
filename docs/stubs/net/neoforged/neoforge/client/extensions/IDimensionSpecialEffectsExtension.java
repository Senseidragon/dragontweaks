Compiled from "IDimensionSpecialEffectsExtension.java"
public interface net.neoforged.neoforge.client.extensions.IDimensionSpecialEffectsExtension {
  public default boolean renderClouds(net.minecraft.client.multiplayer.ClientLevel, int, float, com.mojang.blaze3d.vertex.PoseStack, double, double, double, org.joml.Matrix4f, org.joml.Matrix4f);
  public default boolean renderSky(net.minecraft.client.multiplayer.ClientLevel, int, float, org.joml.Matrix4f, net.minecraft.client.Camera, org.joml.Matrix4f, boolean, java.lang.Runnable);
  public default boolean renderSnowAndRain(net.minecraft.client.multiplayer.ClientLevel, int, float, net.minecraft.client.renderer.LightTexture, double, double, double);
  public default boolean tickRain(net.minecraft.client.multiplayer.ClientLevel, int, net.minecraft.client.Camera);
  public default void adjustLightmapColors(net.minecraft.client.multiplayer.ClientLevel, float, float, float, float, int, int, org.joml.Vector3f);
}
