Compiled from "ObjModelBuilder.java"
public class net.neoforged.neoforge.client.model.generators.loaders.ObjModelBuilder<T extends net.neoforged.neoforge.client.model.generators.ModelBuilder<T>> extends net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder<T> {
  public static <T extends net.neoforged.neoforge.client.model.generators.ModelBuilder<T>> net.neoforged.neoforge.client.model.generators.loaders.ObjModelBuilder<T> begin(T, net.neoforged.neoforge.common.data.ExistingFileHelper);
  public net.neoforged.neoforge.client.model.generators.loaders.ObjModelBuilder<T> modelLocation(net.minecraft.resources.ResourceLocation);
  public net.neoforged.neoforge.client.model.generators.loaders.ObjModelBuilder<T> automaticCulling(boolean);
  public net.neoforged.neoforge.client.model.generators.loaders.ObjModelBuilder<T> shadeQuads(boolean);
  public net.neoforged.neoforge.client.model.generators.loaders.ObjModelBuilder<T> flipV(boolean);
  public net.neoforged.neoforge.client.model.generators.loaders.ObjModelBuilder<T> emissiveAmbient(boolean);
  public net.neoforged.neoforge.client.model.generators.loaders.ObjModelBuilder<T> overrideMaterialLibrary(net.minecraft.resources.ResourceLocation);
  public com.google.gson.JsonObject toJson(com.google.gson.JsonObject);
}
