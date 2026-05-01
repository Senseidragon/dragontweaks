Compiled from "ObjLoader.java"
public class net.neoforged.neoforge.client.model.obj.ObjLoader implements net.neoforged.neoforge.client.model.geometry.IGeometryLoader<net.neoforged.neoforge.client.model.obj.ObjModel>, net.minecraft.server.packs.resources.ResourceManagerReloadListener {
  public static net.neoforged.neoforge.client.model.obj.ObjLoader INSTANCE;
  public net.neoforged.neoforge.client.model.obj.ObjLoader();
  public void onResourceManagerReload(net.minecraft.server.packs.resources.ResourceManager);
  public net.neoforged.neoforge.client.model.obj.ObjModel read(com.google.gson.JsonObject, com.google.gson.JsonDeserializationContext);
  public net.neoforged.neoforge.client.model.obj.ObjModel loadModel(net.neoforged.neoforge.client.model.obj.ObjModel$ModelSettings);
  public net.neoforged.neoforge.client.model.obj.ObjMaterialLibrary loadMaterialLibrary(net.minecraft.resources.ResourceLocation);
  public net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry read(com.google.gson.JsonObject, com.google.gson.JsonDeserializationContext) throws com.google.gson.JsonParseException;
}
