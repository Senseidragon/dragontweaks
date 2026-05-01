Compiled from "ISimpleModelType.java"
public interface com.minecolonies.api.client.render.modeltype.ISimpleModelType extends com.minecolonies.api.client.render.modeltype.IModelType {
  public static final java.lang.String BASE_FOLDER;
  public static final java.lang.String DEFAULT_FOLDER;
  public abstract java.lang.String getTextureBase();
  public abstract int getNumTextures();
  public default net.minecraft.resources.ResourceLocation getTexture(com.minecolonies.api.entity.citizen.AbstractEntityCitizen);
  public default net.minecraft.resources.ResourceLocation getTextureIcon(com.minecolonies.api.entity.citizen.AbstractEntityCitizen);
}
