Compiled from "IBuildingModule.java"
public interface com.minecolonies.api.colony.buildings.modules.IBuildingModule extends com.minecolonies.api.util.IHasDirty {
  public abstract com.minecolonies.api.colony.buildings.IBuilding getBuilding();
  public abstract com.minecolonies.api.colony.buildings.modules.IBuildingModule setBuilding(com.minecolonies.api.colony.buildings.IBuilding);
  public abstract com.minecolonies.api.colony.buildings.modules.IBuildingModule setProducer(com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer);
  public abstract com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer getProducer();
  public default void serializeToView(net.minecraft.network.RegistryFriendlyByteBuf, boolean);
  public default void serializeToView(net.minecraft.network.RegistryFriendlyByteBuf);
}
