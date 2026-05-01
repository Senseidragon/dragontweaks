Compiled from "ItemLayerModelBuilder.java"
public class net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder<T extends net.neoforged.neoforge.client.model.generators.ModelBuilder<T>> extends net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder<T> {
  public static <T extends net.neoforged.neoforge.client.model.generators.ModelBuilder<T>> net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder<T> begin(T, net.neoforged.neoforge.common.data.ExistingFileHelper);
  public net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder<T> emissive(int, int, int...);
  public net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder<T> color(int, int...);
  public net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder<T> renderType(java.lang.String, int...);
  public net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder<T> renderType(net.minecraft.resources.ResourceLocation, int...);
  public com.google.gson.JsonObject toJson(com.google.gson.JsonObject);
}
