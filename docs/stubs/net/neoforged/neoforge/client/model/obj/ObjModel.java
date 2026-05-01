Compiled from "ObjModel.java"
public class net.neoforged.neoforge.client.model.obj.ObjModel extends net.neoforged.neoforge.client.model.geometry.SimpleUnbakedGeometry<net.neoforged.neoforge.client.model.obj.ObjModel> {
  public final boolean automaticCulling;
  public final boolean shadeQuads;
  public final boolean flipV;
  public final boolean emissiveAmbient;
  public final java.lang.String mtlOverride;
  public final net.minecraft.resources.ResourceLocation modelLocation;
  public static net.neoforged.neoforge.client.model.obj.ObjModel parse(net.neoforged.neoforge.client.model.obj.ObjTokenizer, net.neoforged.neoforge.client.model.obj.ObjModel$ModelSettings) throws java.io.IOException;
  public java.util.Set<java.lang.String> getRootComponentNames();
  public java.util.Set<java.lang.String> getConfigurableComponentNames();
  public net.neoforged.neoforge.client.model.renderable.CompositeRenderable bakeRenderable(net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext);
}
