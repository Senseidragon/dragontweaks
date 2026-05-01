Compiled from "IModelType.java"
public interface com.minecolonies.api.client.render.modeltype.IModelType {
  public abstract net.minecraft.resources.ResourceLocation getName();
  public abstract net.minecraft.resources.ResourceLocation getTexture(com.minecolonies.api.entity.citizen.AbstractEntityCitizen);
  public abstract com.minecolonies.api.client.render.modeltype.CitizenModel<com.minecolonies.api.entity.citizen.AbstractEntityCitizen> getMaleModel();
  public abstract com.minecolonies.api.client.render.modeltype.CitizenModel<com.minecolonies.api.entity.citizen.AbstractEntityCitizen> getFemaleModel();
}
