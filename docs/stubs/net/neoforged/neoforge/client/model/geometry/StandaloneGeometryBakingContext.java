Compiled from "StandaloneGeometryBakingContext.java"
public class net.neoforged.neoforge.client.model.geometry.StandaloneGeometryBakingContext implements net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext {
  public static final net.minecraft.resources.ResourceLocation LOCATION;
  public static final net.neoforged.neoforge.client.model.geometry.StandaloneGeometryBakingContext INSTANCE;
  public static net.neoforged.neoforge.client.model.geometry.StandaloneGeometryBakingContext create(net.minecraft.resources.ResourceLocation);
  public static net.neoforged.neoforge.client.model.geometry.StandaloneGeometryBakingContext create(java.util.Map<java.lang.String, net.minecraft.resources.ResourceLocation>);
  public static net.neoforged.neoforge.client.model.geometry.StandaloneGeometryBakingContext create(net.minecraft.resources.ResourceLocation, java.util.Map<java.lang.String, net.minecraft.resources.ResourceLocation>);
  public java.lang.String getModelName();
  public boolean hasMaterial(java.lang.String);
  public net.minecraft.client.resources.model.Material getMaterial(java.lang.String);
  public boolean isGui3d();
  public boolean useBlockLight();
  public boolean useAmbientOcclusion();
  public net.minecraft.client.renderer.block.model.ItemTransforms getTransforms();
  public com.mojang.math.Transformation getRootTransform();
  public net.minecraft.resources.ResourceLocation getRenderTypeHint();
  public boolean isComponentVisible(java.lang.String, boolean);
  public static net.neoforged.neoforge.client.model.geometry.StandaloneGeometryBakingContext$Builder builder();
  public static net.neoforged.neoforge.client.model.geometry.StandaloneGeometryBakingContext$Builder builder(net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext);
}
