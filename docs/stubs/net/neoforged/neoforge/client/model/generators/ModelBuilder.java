Compiled from "ModelBuilder.java"
public class net.neoforged.neoforge.client.model.generators.ModelBuilder<T extends net.neoforged.neoforge.client.model.generators.ModelBuilder<T>> extends net.neoforged.neoforge.client.model.generators.ModelFile {
  public T parent(net.neoforged.neoforge.client.model.generators.ModelFile);
  public T texture(java.lang.String, java.lang.String);
  public T texture(java.lang.String, net.minecraft.resources.ResourceLocation);
  public T renderType(java.lang.String);
  public T renderType(net.minecraft.resources.ResourceLocation);
  public net.neoforged.neoforge.client.model.generators.ModelBuilder<T>.TransformsBuilder transforms();
  public T ao(boolean);
  public T guiLight(net.minecraft.client.renderer.block.model.BlockModel$GuiLight);
  public net.neoforged.neoforge.client.model.generators.ModelBuilder<T>.ElementBuilder element();
  public net.neoforged.neoforge.client.model.generators.ModelBuilder<T>.ElementBuilder element(int);
  public int getElementCount();
  public <L extends net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder<T>> L customLoader(java.util.function.BiFunction<T, net.neoforged.neoforge.common.data.ExistingFileHelper, L>);
  public net.neoforged.neoforge.client.model.generators.ModelBuilder<T>.RootTransformsBuilder rootTransforms();
  public com.google.gson.JsonObject toJson();
}
