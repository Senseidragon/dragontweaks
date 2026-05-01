Compiled from "IEntityListModuleView.java"
public interface com.minecolonies.api.colony.buildings.modules.IEntityListModuleView extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView {
  public abstract void addEntity(net.minecraft.resources.ResourceLocation);
  public abstract boolean isAllowedEntity(net.minecraft.resources.ResourceLocation);
  public abstract int getSize();
  public abstract void removeEntity(net.minecraft.resources.ResourceLocation);
  public abstract java.lang.String getId();
  public abstract boolean isInverted();
  public abstract void clearEntities();
}
