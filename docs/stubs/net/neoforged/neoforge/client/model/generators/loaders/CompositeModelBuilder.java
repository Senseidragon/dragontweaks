Compiled from "CompositeModelBuilder.java"
public class net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder<T extends net.neoforged.neoforge.client.model.generators.ModelBuilder<T>> extends net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder<T> {
  public static <T extends net.neoforged.neoforge.client.model.generators.ModelBuilder<T>> net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder<T> begin(T, net.neoforged.neoforge.common.data.ExistingFileHelper);
  public net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder<T> child(java.lang.String, T);
  public net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder<T> itemRenderOrder(java.lang.String...);
  public com.google.gson.JsonObject toJson(com.google.gson.JsonObject);
}
