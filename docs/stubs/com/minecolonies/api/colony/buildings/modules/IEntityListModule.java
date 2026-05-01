Compiled from "IEntityListModule.java"
public interface com.minecolonies.api.colony.buildings.modules.IEntityListModule {
  public abstract void addEntity(net.minecraft.resources.ResourceLocation);
  public abstract boolean isEntityInList(net.minecraft.resources.ResourceLocation);
  public abstract void removeEntity(net.minecraft.resources.ResourceLocation);
  public abstract java.util.List<net.minecraft.resources.ResourceLocation> getList();
  public abstract java.lang.String getListIdentifier();
  public abstract java.lang.String getId();
}
