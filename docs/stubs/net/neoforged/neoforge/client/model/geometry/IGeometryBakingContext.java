Compiled from "IGeometryBakingContext.java"
public interface net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext {
  public abstract java.lang.String getModelName();
  public abstract boolean hasMaterial(java.lang.String);
  public abstract net.minecraft.client.resources.model.Material getMaterial(java.lang.String);
  public abstract boolean isGui3d();
  public abstract boolean useBlockLight();
  public abstract boolean useAmbientOcclusion();
  public abstract net.minecraft.client.renderer.block.model.ItemTransforms getTransforms();
  public abstract com.mojang.math.Transformation getRootTransform();
  public abstract net.minecraft.resources.ResourceLocation getRenderTypeHint();
  public abstract boolean isComponentVisible(java.lang.String, boolean);
  public default net.neoforged.neoforge.client.RenderTypeGroup getRenderType(net.minecraft.resources.ResourceLocation);
}
