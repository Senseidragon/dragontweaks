Compiled from "IModelTypeRegistry.java"
public interface com.minecolonies.api.client.render.modeltype.registry.IModelTypeRegistry {
  public static com.minecolonies.api.client.render.modeltype.registry.IModelTypeRegistry getInstance();
  public abstract void register(com.minecolonies.api.client.render.modeltype.IModelType);
  public abstract com.minecolonies.api.client.render.modeltype.IModelType getModelType(net.minecraft.resources.ResourceLocation);
}
