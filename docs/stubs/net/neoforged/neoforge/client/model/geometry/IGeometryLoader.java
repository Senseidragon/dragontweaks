Compiled from "IGeometryLoader.java"
public interface net.neoforged.neoforge.client.model.geometry.IGeometryLoader<T extends net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry<T>> {
  public abstract T read(com.google.gson.JsonObject, com.google.gson.JsonDeserializationContext) throws com.google.gson.JsonParseException;
}
